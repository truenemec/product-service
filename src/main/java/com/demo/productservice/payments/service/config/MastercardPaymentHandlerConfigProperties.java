package com.demo.productservice.payments.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("mastercard-handler")
@Getter
@Setter
@Validated
public class MastercardPaymentHandlerConfigProperties {
    @NotEmpty
    private String defaultPath;
}
