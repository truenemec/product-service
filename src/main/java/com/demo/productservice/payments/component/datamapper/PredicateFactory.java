package com.demo.productservice.payments.component.datamapper;

import com.demo.productservice.payments.component.datamapper.config.common.Rule;
import com.demo.productservice.payments.dto.VisaColumn;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateFactory {

    private static Map<String, Function<VisaColumn, ?>> map = Map.
            ofEntries(Map.entry("title", VisaColumn::getTitle),
                    Map.entry("cred", VisaColumn::getCred));

    public static Predicate<VisaColumn> create(Rule rule){
        return (val) -> rule.getColumns().stream().allMatch(column -> {
            Object value = map.get(column.getName()).apply(val);
            String type = column.getType().toUpperCase();
            if (type.equals("STRING")) {
                return column.getValues().stream().anyMatch(v -> v.equals(value));
            }
            if (type.equals("BIGDECIMAL")) {
                return column.getValues().stream().map(BigDecimal::new).anyMatch(v -> v.equals(value));
            }
            return false;
        });
    }

    public static Predicate<VisaColumn> create(List<Rule> rules){
        return rules.stream().map(PredicateFactory::create).reduce(Predicate::or).orElse(v -> false);
    }
}
