package com.demo.productservice.payments.service;

import com.demo.productservice.payments.dto.PaymentDto;
import com.demo.productservice.payments.type.PaymentProviderType;

public interface PaymentService {
    void handle(PaymentDto paymentDto);
}
