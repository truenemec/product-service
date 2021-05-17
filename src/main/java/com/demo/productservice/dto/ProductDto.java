package com.demo.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ProductDto {
    private Long productId;
    private String name;
    private Long categoryId;
}
