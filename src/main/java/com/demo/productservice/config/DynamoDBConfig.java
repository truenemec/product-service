package com.demo.productservice.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AwsConfigProperties.class)
@RequiredArgsConstructor
public class DynamoDBConfig {

    final AwsConfigProperties awsConfigProperties;

    @Bean
    public AwsClientBuilder.EndpointConfiguration dynamoDBEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                awsConfigProperties.getDynamoDBEndpoint(),
                awsConfigProperties.getRegion());
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider())
                .withEndpointConfiguration(dynamoDBEndpoint())
                .build();
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                awsConfigProperties.getAccessKey(), awsConfigProperties.getAccessKey());
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(amazonDynamoDB());
    }
}
