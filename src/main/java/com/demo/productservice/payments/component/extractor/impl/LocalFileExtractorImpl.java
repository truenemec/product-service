package com.demo.productservice.payments.component.extractor.impl;

import com.demo.productservice.payments.component.extractor.FileExtractor;
import com.demo.productservice.payments.component.extractor.PathContext;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LocalFileExtractorImpl implements FileExtractor {
    @Override
    @SneakyThrows
    public Stream<PathContext> getFiles(PathContext context) {
        return Files.list(context.getPath()).map(PathContext::new);
    }

    @Override
    @SneakyThrows
    public void execute(PathContext context, Consumer<InputStream> fileHandler) {
        fileHandler.accept(Files.newInputStream(context.getPath()));
    }
}
