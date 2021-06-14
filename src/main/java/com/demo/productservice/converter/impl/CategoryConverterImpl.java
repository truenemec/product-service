package com.demo.productservice.converter.impl;

import com.demo.productservice.converter.CategoryConverter;
import com.demo.productservice.domain.Category;
import com.demo.productservice.domain.Product;
import com.demo.productservice.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class CategoryConverterImpl implements CategoryConverter {
    @Override
    public Category convertToDomain(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .version(categoryDto.getVersion())
                .build();
    }

    @Override
    public CategoryDto convertToDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .productIds(Stream.ofNullable(category.getProducts()).flatMap(Collection::stream)
                        .map(Product::getId).collect(Collectors.toList()))
                .version(category.getVersion())
                .build();
    }
}
