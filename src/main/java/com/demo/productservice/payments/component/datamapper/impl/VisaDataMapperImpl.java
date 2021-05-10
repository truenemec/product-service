package com.demo.productservice.payments.component.datamapper.impl;

import com.demo.productservice.payments.component.datamapper.*;
import com.demo.productservice.payments.component.datamapper.config.VisaDataMapperConfig;
import com.demo.productservice.payments.dto.VisaColumn;
import com.demo.productservice.payments.model.Item;
import com.demo.productservice.payments.model.Metadata;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(VisaDataMapperConfig.class)
@Getter
public class VisaDataMapperImpl implements DataMapper {
    private final static String DEBIT_SUM = "DEB_SUM";
    private final static String CREDIT_SUM = "CREDIT_SUM";
    private final VisaDataMapperConfig visaDataMapperConfig;

    @Override
    public Optional<DataMapperResult> map(String line, MapperContext context) {
        StatisticAware output = null;

        if(isColumnHeader(context)){
            output = handleColumnRow(line, context);
        }
        if(isDataRow(context)){
            output = handleDataRow(line, context);
        }

        DataMapperResult result = DataMapperResult
                .builder()
                .data(output)
                .build();
        return Optional.of(result);
    }

    private boolean isColumnHeader(MapperContext context){
        long currentLine = context.getProceedLines() + 1;
        return currentLine == getVisaDataMapperConfig().getHeaderColumnNumber();
    }

    private boolean isDataRow(MapperContext context){
        long currentLine = context.getProceedLines() + 1;
        return currentLine != getVisaDataMapperConfig().getHeaderColumnNumber();
    }

    private StatisticAware handleColumnRow(String line, MapperContext context){
        context.setColumnMap(columnMap(line));
        return Metadata.builder()
                .columnMap(context.getColumnMap())
                .build();
    }

    private StatisticAware handleDataRow(String line, MapperContext context){
        VisaColumn visaColumn = parseDataColumn(line, context);
        Item item = convert(visaColumn);
        aggregate(item, context);
        return item;
    }

    private void aggregate(Item item, MapperContext context){
        Utils.sum(context, DEBIT_SUM, item::getDebit);
        Utils.sum(context, CREDIT_SUM, item::getCredit);
    }

    @Override
    public List<DataMapperResult> generateFooter(MapperContext context) {
        Item total = Item.builder()
                .title("---")
                .credit(new BigDecimal(context.getAggregation().getOrDefault(DEBIT_SUM, "0")))
                .debit(new BigDecimal(context.getAggregation().getOrDefault(CREDIT_SUM, "0")))
                .build();
        DataMapperResult result = DataMapperResult
                .builder()
                .data(total)
                .build();
        return Collections.singletonList(result);
    }

    private VisaColumn parseDataColumn(String line, MapperContext context){
        String[] values = line.split(getVisaDataMapperConfig().getFileDelimiter());
        return VisaColumn.builder()
                .title(values[Utils.getColumnPosition(visaDataMapperConfig.getColumnMap().getTitle(), context)])
                .cred(new BigDecimal(values[Utils.getColumnPosition(visaDataMapperConfig.getColumnMap().getDebit(),context)].trim()))
                .deb(new BigDecimal(values[Utils.getColumnPosition(visaDataMapperConfig.getColumnMap().getCredit(), context)].trim()))
                .build();
    }

    private Map<String, Integer> columnMap(String line){
        String[] values = line.split(getVisaDataMapperConfig().getFileDelimiter());
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < values.length; i++){
            map.put(values[i].trim(),i);
        }
        return map;
    }

    private Item convert(VisaColumn visaColumn){
        return Item.builder()
                .title(visaColumn.getTitle())
                .debit(visaColumn.getDeb())
                .credit(visaColumn.getCred())
                .build();
    }
}
