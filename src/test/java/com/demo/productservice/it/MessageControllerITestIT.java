package com.demo.productservice.it;

import com.demo.productservice.converter.MessageConverter;
import com.demo.productservice.domain.MessageItem;
import com.demo.productservice.dto.MessageDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.UUID;


public class MessageControllerITestIT extends ControllerITestIT {
    @Autowired
    private MessageConverter messageConverter;

    @Override
    protected String getApiPath() {
        return "api/v1/message";
    }

    @Test
    public void should_save_item() {
        MessageDto messageDto = MessageDto.builder().build();
        getWebTestClient()
                .post()
                .uri(getApiPath())
                .bodyValue(messageDto)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.guid")
                .isNotEmpty();
    }

    @Test
    public void should_get_item() {
        MessageItem messageItem = MessageItem.builder()
                .guid(UUID.randomUUID().toString())
                .message(RandomStringUtils.randomAlphabetic(20))
                .severity(RandomStringUtils.randomAlphabetic(20))
                .build();
        getDynamoDBMapper().save(messageItem);

        getWebTestClient()
                .get()
                .uri(getApiPath() + "/" + messageItem.getGuid())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(MessageDto.class)
                .isEqualTo(messageConverter.convertToDto(messageItem));
    }

    @Test
    public void should_return_not_found_for_get_item() {

        getWebTestClient()
                .get()
                .uri(getApiPath() + "/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void should_get_item_by_severity() {
        MessageItem messageItem1 = MessageItem.builder()
                .guid(UUID.randomUUID().toString())
                .message(RandomStringUtils.randomAlphabetic(20))
                .severity("1")
                .build();
        MessageItem messageItem2 = MessageItem.builder()
                .guid(UUID.randomUUID().toString())
                .message(RandomStringUtils.randomAlphabetic(20))
                .severity("1")
                .build();
        MessageItem messageItem3 = MessageItem.builder()
                .guid(UUID.randomUUID().toString())
                .message(RandomStringUtils.randomAlphabetic(20))
                .severity("2")
                .build();
        getDynamoDBMapper().save(messageItem1);
        getDynamoDBMapper().save(messageItem2);
        getDynamoDBMapper().save(messageItem3);

        getWebTestClient()
                .get()
                .uri(getApiPath() + "/" + "severity/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.numberOfElements", 2);

    }
}
