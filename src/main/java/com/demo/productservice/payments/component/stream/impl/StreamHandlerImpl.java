package com.demo.productservice.payments.component.stream.impl;

import com.demo.productservice.payments.component.datamapper.StatisticAware;
import com.demo.productservice.payments.model.Item;
import com.demo.productservice.payments.component.datamapper.DataMapper;
import com.demo.productservice.payments.component.datamapper.DataMapperResult;
import com.demo.productservice.payments.component.datamapper.MapperContext;
import com.demo.productservice.payments.component.stream.StreamHandler;
import com.demo.productservice.payments.component.stream.impl.config.StreamHandlerConfig;
import com.demo.productservice.payments.model.ItemAggregated;
import com.demo.productservice.payments.model.Metadata;
import com.demo.productservice.payments.model.Statistic;
import com.demo.productservice.payments.repository.ItemAggregatedRepository;
import com.demo.productservice.payments.repository.ItemRepository;
import com.demo.productservice.payments.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
@EnableConfigurationProperties(StreamHandlerConfig.class)
public class StreamHandlerImpl implements StreamHandler {
    private final StreamHandlerConfig streamHandlerConfig;
    private final ItemRepository itemRepository;
    private final ItemAggregatedRepository itemAggregatedRepository;
    private final MetadataRepository metadataRepository;

    @Override
    @SneakyThrows
    public void proceed(InputStream inputStream, DataMapper dataMapper, MapperContext context) {
        initContext(context);
        try(BufferedInputStreamReader reader = new BufferedInputStreamReader(inputStream)){
            String line;
            reader.skip(context.getProceedBytes());
            while((line = reader.readLine()) != null){
                Optional<DataMapperResult> result = dataMapper.map(line, context);

                context.setProceedLines(context.getProceedLines() + 1);
                context.setProceedBytes(reader.getOffset());

                result.ifPresent(val -> handleMapperResult(val, context));

                log.info(result + " lineNumber = " + context.getProceedLines()  +
                        " readBytes = " + context.getProceedBytes());
                if(needToFlush(context)){
                    flush(context);
                }
            }
        }
        dataMapper.generateFooter(context).forEach(val -> handleMapperResult(val, context));
        flush(context);
    }
    private boolean needToFlush(MapperContext context){
        long batchByteProceeded = context.getProceedBytes() - context.getFlushedBytes();
        long batchLineProceeded = context.getProceedLines()- context.getFlushedLines();
        return batchByteProceeded >= streamHandlerConfig.getMaxByteThreshold() ||
                batchLineProceeded >= streamHandlerConfig.getMaxLineThreshold();
    }

    private void initContext(MapperContext context){
        Statistic statistic = getAllStatisticByFileName(context.getFileName());
        context.setProceedBytes(statistic.getProceedBytes());
        context.setProceedLines(statistic.getProceedLines());
        context.setFlushedBytes(statistic.getProceedBytes());
        context.setFlushedLines(statistic.getProceedLines());
        context.setAggregation(statistic.getAggregation());
        metadataRepository.getByKey(context.getFileName())
                .ifPresent(val -> context.setColumnMap(val.getColumnMap()));
    }

    private void flush(MapperContext context){
        context.getDataMapperResults().forEach(dataMapperResult -> {
            Object data = dataMapperResult.getData();
            if(data instanceof Item){
                itemRepository.save((Item)data);
            }
            else if(data instanceof ItemAggregated){
                itemAggregatedRepository.save((ItemAggregated)data);
            }
            else if(data instanceof Metadata){
                metadataRepository.save((Metadata)data);
            }
        });
        context.getDataMapperResults().clear();
        context.setFlushedLines(context.getProceedLines());
        context.setFlushedBytes(context.getProceedBytes());
    }
    private Statistic fillStatistic(MapperContext context){
        return Statistic.builder()
                .fileName(context.getFileName())
                .proceedLines(context.getProceedLines())
                .currentLine(context.getProceedLines())
                .proceedBytes(context.getProceedBytes())
                .aggregation(context.getAggregation())
                .build();
    }

    private Statistic getAllStatisticByFileName(String fileName){
        return itemRepository.getByFileNameLatest(fileName)
                .map(StatisticAware::getStatistic)
                .orElseGet(Statistic::new);
    }
    private void handleMapperResult(DataMapperResult result, MapperContext context){
        context.getDataMapperResults().add(result);
        result.getData().setStatistic(fillStatistic(context));
    }
}
