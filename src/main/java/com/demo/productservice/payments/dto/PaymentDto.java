package com.demo.productservice.payments.dto;

import com.demo.productservice.payments.type.PaymentProviderType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PaymentDto {
    private LocalDate from;
    private LocalDate to;
    private PaymentProviderType paymentProviderType;
}
