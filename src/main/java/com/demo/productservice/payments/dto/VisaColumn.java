package com.demo.productservice.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class VisaColumn {
    private String title;
    private BigDecimal deb;
    private BigDecimal cred;
}
