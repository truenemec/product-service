package com.demo.productservice.payments.component.datamapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class DataMapperResult {
    private final StatisticAware data;
}
