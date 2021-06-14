package com.demo.productservice.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
public class TestDto2 {
    @NotNull
    @NotBlank
    BigDecimal tax;
}
