package com.demo.productservice.payments.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("visa-handler")
@Getter
@Setter
@Validated
public class VisaPaymentHandlerConfigProperties {
    @NotEmpty
    private String defaultPath;
}
