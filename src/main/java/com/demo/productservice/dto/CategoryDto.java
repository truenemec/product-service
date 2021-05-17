package com.demo.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String name;
    private List<Long> productIds;
    private Integer version;
}
