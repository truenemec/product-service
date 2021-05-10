package com.demo.productservice.payments.service;

import com.demo.productservice.payments.service.impl.PaymentHandlerContext;
import com.demo.productservice.payments.type.PaymentProviderType;

public interface PaymentHandler {
    void handle(PaymentHandlerContext paymentHandlerContext);
    PaymentProviderType getPaymentProviderType();
}
