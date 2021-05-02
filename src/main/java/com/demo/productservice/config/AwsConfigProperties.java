package com.demo.productservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("amazon.aws")
@Getter
@Setter
public class AwsConfigProperties {
    private String accessKey;
    private String secretKey;
    private String dynamoDBEndpoint;
    private String region;
}
