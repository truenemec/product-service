package com.demo.productservice.payments.service.impl;

import com.demo.productservice.payments.component.datamapper.impl.VisaDataMapperImpl;
import com.demo.productservice.payments.component.extractor.FileExtractor;
import com.demo.productservice.payments.component.extractor.PathContext;
import com.demo.productservice.payments.component.extractor.impl.LocalFileExtractorImpl;
import com.demo.productservice.payments.component.stream.StreamHandler;
import com.demo.productservice.payments.service.PaymentHandler;
import com.demo.productservice.payments.service.config.VisaPaymentHandlerConfigProperties;
import com.demo.productservice.payments.type.PaymentProviderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
@Getter
@RequiredArgsConstructor
@EnableConfigurationProperties(VisaPaymentHandlerConfigProperties.class)
public class VisaPaymentHandler extends AbstractPaymentHandlerImpl implements PaymentHandler {
    private PaymentProviderType paymentProviderType = PaymentProviderType.VISA;
    private final StreamHandler streamHandler;
    private final VisaDataMapperImpl dataMapper;
    private final VisaPaymentHandlerConfigProperties properties;

    @Override
    FileExtractor getFileExtractor() {
        return new LocalFileExtractorImpl();
    }

    @Override
    PathContext getDefaultPath() {
        return PathContext.builder()
                .path(Paths.get(properties.getDefaultPath()))
                .build();
    }

}
