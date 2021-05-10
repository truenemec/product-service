package com.demo.productservice.payments.component.datamapper.impl;

import com.demo.productservice.payments.component.datamapper.DataMapper;
import com.demo.productservice.payments.component.datamapper.DataMapperResult;
import com.demo.productservice.payments.component.datamapper.MapperContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MastercardDataMapperImpl implements DataMapper {
    @Override
    public Optional<DataMapperResult> map(String line, MapperContext context) {
        DataMapperResult result = DataMapperResult.builder().data(null).build();
        return Optional.of(result);
    }

    @Override
    public List<DataMapperResult> generateFooter(MapperContext context) {
        return Collections.emptyList();
    }
}
