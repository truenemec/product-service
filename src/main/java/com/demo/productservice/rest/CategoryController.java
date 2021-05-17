package com.demo.productservice.rest;

import com.demo.productservice.converter.CategoryConverter;
import com.demo.productservice.dto.CategoryDto;
import com.demo.productservice.exception.NotFoundException;
import com.demo.productservice.service.CategoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@Getter
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryConverter categoryConverter;

    @GetMapping("{id}")
    public CategoryDto findById(@PathVariable Long id){
        return getCategoryService().findById(id)
                .map(getCategoryConverter()::convertToDto)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public CategoryDto save(@RequestBody CategoryDto categoryDto){
        return Optional.of(categoryDto)
                .map(getCategoryConverter()::convertToDomain)
                .map(getCategoryService()::save)
                .map(getCategoryConverter()::convertToDto)
                .get();

    }

}
