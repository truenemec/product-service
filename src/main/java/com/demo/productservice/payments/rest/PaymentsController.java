package com.demo.productservice.payments.rest;

import com.demo.productservice.payments.dto.PaymentDto;
import com.demo.productservice.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentsController {
    final private PaymentService paymentService;
    @PostMapping
    public void handlePayment(@RequestBody PaymentDto paymentDto){
        paymentService.handle(paymentDto);
    }
}
