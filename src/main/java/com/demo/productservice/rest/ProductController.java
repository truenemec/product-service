package com.demo.productservice.rest;

import com.demo.productservice.converter.ProductConverter;
import com.demo.productservice.dto.ProductDto;
import com.demo.productservice.exception.NotFoundException;
import com.demo.productservice.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Getter
public class ProductController {

    private final ProductService productService;

    private final ProductConverter productConverter;

    @GetMapping("{id}")
    public ProductDto findById(@PathVariable Long id){
        return getProductService().findById(id)
                .map(getProductConverter()::convertToDto)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ProductDto save(@Valid @RequestBody ProductDto productDto){
        return Optional.of(productDto)
                .map(getProductConverter()::convertToDomain)
                .map(getProductService()::save)
                .map(getProductConverter()::convertToDto)
                .get();

    }
}
