package com.demo.productservice.service.impl;

import com.demo.productservice.domain.Category;
import com.demo.productservice.domain.Product;
import com.demo.productservice.repository.CategoryRepository;
import com.demo.productservice.repository.ProductRepository;
import com.demo.productservice.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
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
    public Product save(Product product) {
        product.setCategory(categoryRepository.getOne(product.getCategory().getId()));
        return getProductRepository().save(product);
    }
}
