package com.demo.productservice.service;

import com.demo.productservice.domain.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
}
