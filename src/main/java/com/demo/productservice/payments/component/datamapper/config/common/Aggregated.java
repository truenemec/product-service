package com.demo.productservice.payments.component.datamapper.config.common;

import lombok.Data;

import java.util.List;

@Data
public class Aggregated {
    private List<Rule> rules;
}
