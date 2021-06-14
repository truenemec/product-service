package com.demo.productservice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;


@Builder
@Value
public class CategoryDto {
    Long categoryId;
    String name;
    List<Long> productIds;
    Integer version;
}
