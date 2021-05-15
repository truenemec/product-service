package com.demo.productservice.payments.component.extractor.impl;

import com.demo.productservice.payments.component.extractor.FileExtractor;
import com.demo.productservice.payments.component.extractor.PathContext;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SftpFileExtractor implements FileExtractor {
    public SftpFileExtractor(){
//        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
//        factory.setHost("localhost");
//        factory.setPort(port);
//        factory.setUser("foo");
//        factory.setPassword("foo");
//        factory.setAllowUnknownKeys(true);
//        factory.setTestSession(true);
//        SftpRemoteFileTemplate template = new SftpRemoteFileTemplate(sessionFactory);
    }

    @Override
    public Stream<PathContext> getFiles(PathContext context) {
        return null;
    }

    @Override
    public void execute(PathContext context, Consumer<InputStream> fileHandler) {

    }
}
