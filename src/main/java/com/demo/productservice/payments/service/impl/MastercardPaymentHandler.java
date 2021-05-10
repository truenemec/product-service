package com.demo.productservice.payments.service.impl;

import com.demo.productservice.payments.component.datamapper.impl.MastercardDataMapperImpl;
import com.demo.productservice.payments.component.extractor.FileExtractor;
import com.demo.productservice.payments.component.extractor.PathContext;
import com.demo.productservice.payments.component.extractor.impl.LocalFileExtractorImpl;
import com.demo.productservice.payments.component.stream.StreamHandler;
import com.demo.productservice.payments.service.PaymentHandler;
import com.demo.productservice.payments.service.config.MastercardPaymentHandlerConfigProperties;
import com.demo.productservice.payments.type.PaymentProviderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
@Getter
@RequiredArgsConstructor
@EnableConfigurationProperties(MastercardPaymentHandlerConfigProperties.class)
public class MastercardPaymentHandler extends AbstractPaymentHandlerImpl implements PaymentHandler {
    private PaymentProviderType paymentProviderType = PaymentProviderType.MASTERCARD;
    private final StreamHandler streamHandler;
    private final MastercardDataMapperImpl dataMapper;
    private final MastercardPaymentHandlerConfigProperties properties;

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
