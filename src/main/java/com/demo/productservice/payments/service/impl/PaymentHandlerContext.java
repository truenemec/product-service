package com.demo.productservice.payments.service.impl;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class PaymentHandlerContext {
    private LocalDate from;
    private LocalDate to;
}
