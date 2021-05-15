package com.demo.productservice.payments.component.datamapper;

import com.demo.productservice.payments.model.ItemAggregated;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MapperContext {
    private final String fileName;
    private long proceedBytes = 0;
    private long proceedLines = 0;
    private List<DataMapperResult> dataMapperResults = new ArrayList<>();
    private Map<String, Integer> columnMap = new HashMap<>();
    private Map<String, String> aggregation = new HashMap<>();
    private long flushedBytes;
    private long flushedLines;
    private Map<ItemAggregated.Key, ItemAggregated> itemAggregatedMap = new HashMap<>();
}
