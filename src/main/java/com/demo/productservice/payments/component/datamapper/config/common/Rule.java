package com.demo.productservice.payments.component.datamapper.config.common;

import lombok.Data;

import java.util.List;

@Data
public class Rule {
    private List<ColumnRule> columns;
}
