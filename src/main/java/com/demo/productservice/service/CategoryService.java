package com.demo.productservice.service;

import com.demo.productservice.domain.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
    Category save(Category category);
}
