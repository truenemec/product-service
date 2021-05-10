package com.demo.productservice.payments.service.impl;

import com.demo.productservice.payments.component.datamapper.DataMapper;
import com.demo.productservice.payments.component.datamapper.MapperContext;
import com.demo.productservice.payments.component.extractor.FileExtractor;
import com.demo.productservice.payments.component.extractor.PathContext;
import com.demo.productservice.payments.component.stream.StreamHandler;
import com.demo.productservice.payments.service.PaymentHandler;

public abstract class AbstractPaymentHandlerImpl implements PaymentHandler {

    abstract FileExtractor getFileExtractor();

    abstract StreamHandler getStreamHandler();

    abstract PathContext getDefaultPath();

    abstract DataMapper getDataMapper();

    @Override
    public void handle(PaymentHandlerContext paymentHandlerContext) {
        getFileExtractor()
                .getFiles(getDefaultPath())
                .forEach(fileContext -> getFileExtractor()
                        .execute(fileContext, stream -> getStreamHandler().proceed(stream, getDataMapper(), new
                                MapperContext(fileContext.getPath().getFileName().toString()))));

    }
}
