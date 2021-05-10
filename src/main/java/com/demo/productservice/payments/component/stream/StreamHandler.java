package com.demo.productservice.payments.component.stream;

import com.demo.productservice.payments.component.datamapper.DataMapper;
import com.demo.productservice.payments.component.datamapper.MapperContext;

import java.io.InputStream;

public interface StreamHandler {
    void proceed(InputStream inputStream, DataMapper dataMapper, MapperContext context);
}
