package com.demo.productservice.payments.component.extractor;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface FileExtractor {
    Stream<PathContext> getFiles(PathContext context);
    void execute(PathContext context, Consumer<InputStream> fileHandler);
}
