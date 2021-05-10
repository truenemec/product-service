package com.demo.productservice.payments.service.impl;

import com.demo.productservice.payments.dto.PaymentDto;
import com.demo.productservice.payments.service.PaymentHandler;
import com.demo.productservice.payments.service.PaymentService;
import com.demo.productservice.payments.type.PaymentProviderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Getter
public class PaymentServiceImpl implements PaymentService {

    private final Map<PaymentProviderType, PaymentHandler> paymentHandlersMap;

    public PaymentServiceImpl(@Autowired List<PaymentHandler> paymentHandlers){
        paymentHandlersMap = paymentHandlers
                .stream()
                .collect(Collectors
                        .toMap(PaymentHandler::getPaymentProviderType, val -> val));
    }

    @Override
    public void handle(PaymentDto paymentDto) {
        PaymentHandlerContext context = PaymentHandlerContext
                .builder()
                .from(paymentDto.getFrom())
                .from(paymentDto.getTo())
                .build();

        PaymentHandler paymentHandler = paymentHandlersMap.get(paymentDto.getPaymentProviderType());

        if(paymentHandler == null){
            throw new IllegalArgumentException("unrecognized payment provider "
                    + paymentDto.getPaymentProviderType());
        }

        paymentHandler.handle(context);

    }
}
