package com.demo.productservice.converter.impl;

import com.demo.productservice.converter.ProductConverter;
import com.demo.productservice.domain.Category;
import com.demo.productservice.domain.Product;
import com.demo.productservice.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverterImpl implements ProductConverter {
    @Override
    public Product convertToDomain(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getProductId())
                .name(productDto.getName())
                .category(Category.builder()
                        .id(productDto.getCategoryId())
                        .build())
                .build();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .productId(product.getId())
                .name(product.getName())
                .categoryId(product.getCategory().getId())
                .build();
    }
}
