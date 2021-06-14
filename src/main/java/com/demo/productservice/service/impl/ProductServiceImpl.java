package com.demo.productservice.service.impl;

import com.demo.productservice.domain.Product;
import com.demo.productservice.repository.CategoryRepository;
import com.demo.productservice.repository.ProductRepository;
import com.demo.productservice.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Getter
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return getProductRepository().findById(id);
    }

    @Override
    public Product save(final Product product) {
        Product productToSave = product.toBuilder()
                .category(categoryRepository.getOne(product.getCategory().getId()))
                .build();
        return getProductRepository().save(productToSave);
    }
}
