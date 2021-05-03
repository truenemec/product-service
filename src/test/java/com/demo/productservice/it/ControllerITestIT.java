package com.demo.productservice.it;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.demo.productservice.domain.MessageItem;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.DYNAMODB;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Getter
@Setter
@ActiveProfiles("test")
@Testcontainers
@ContextConfiguration(initializers = {ControllerITestIT.LocalStackInitializer.class})
@AutoConfigureWebTestClient
public abstract class ControllerITestIT {

    private static DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:0.11.3");

    @Container
    private static LocalStackContainer localStackContainer = new LocalStackContainer(localstackImage)
            .withServices(DYNAMODB);

   public static class LocalStackInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "amazon.aws.dynamo-db-endpoint=" + localStackContainer
                            .getEndpointConfiguration(DYNAMODB).getServiceEndpoint(),
                    "amazon.aws.secret-key=" + localStackContainer.getSecretKey(),
                    "amazon.aws.access-key=" + localStackContainer.getAccessKey()
            );
        }
    }

    @BeforeAll
    public static void initDB(@Autowired DynamoDBMapper dynamoDBMapper, @Autowired AmazonDynamoDB amazonDynamoDB){
        var messageTableRequest = dynamoDBMapper.generateCreateTableRequest(MessageItem.class);
        messageTableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(messageTableRequest);
    }

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    protected abstract String getApiPath();

}
