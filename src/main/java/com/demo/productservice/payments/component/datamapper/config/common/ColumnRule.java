package com.demo.productservice.payments.component.datamapper.config.common;

import lombok.Data;

import java.util.List;

@Data
public class ColumnRule {
    private String name;
    private String type;
    private List<String> values;
}
