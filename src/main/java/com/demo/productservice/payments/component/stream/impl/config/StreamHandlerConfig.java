package com.demo.productservice.payments.component.stream.impl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("stream")
@Getter
@Setter
public class StreamHandlerConfig {
    private long maxByteThreshold = 1024;
    private long maxLineThreshold = 50;
}
