package com.demo.productservice.service.impl;

import com.demo.productservice.domain.Category;
import com.demo.productservice.repository.CategoryRepository;
import com.demo.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
