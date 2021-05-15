package com.demo.productservice.payments.component.datamapper;

import com.demo.productservice.payments.component.datamapper.config.common.Column;

import java.math.BigDecimal;
import java.util.function.Supplier;

public interface Utils {
    static void sum(MapperContext context, String aggregationName, Supplier<BigDecimal> getter){
        BigDecimal val = new BigDecimal(context.getAggregation().getOrDefault(aggregationName, "0"));
        val = val.add(getter.get());
        context.getAggregation().put(aggregationName, val.toString());
    }

    static Integer getColumnPosition(Column column, MapperContext context){
        return context.getColumnMap().get(column.getColumnName());
    }
}
