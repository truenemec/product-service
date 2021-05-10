package com.demo.productservice.payments.component.extractor;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@RequiredArgsConstructor
@Builder
@Getter
public class PathContext {
    private final Path path;
}
