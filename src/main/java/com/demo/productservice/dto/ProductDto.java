package com.demo.productservice.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Set;


@Builder
@Value
public class ProductDto {
    Long productId;

    String name;
    BigDecimal price;
    Long categoryId;
    @Valid
    Set<TestDto> testDtos;
}
