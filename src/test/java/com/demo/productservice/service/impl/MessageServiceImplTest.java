package com.demo.productservice.service.impl;

import com.demo.productservice.domain.MessageItem;
import com.demo.productservice.repository.MessageRepository;
import com.demo.productservice.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.annotation.EnableRetry;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@EnableRetry
@SpringBootTest(classes={MessageServiceImpl.class})
public class MessageServiceImplTest {
    @MockBean
    private MessageRepository messageRepository;
    @Autowired
    private MessageService messageService;

    @Test
    public void should_handle_retryable(){
        MessageItem messageItem = MessageItem
                .builder()
                .guid(UUID.randomUUID().toString())
                .build();

        doThrow(new IllegalStateException()).when(messageRepository).save(any());

        assertThrows(IllegalStateException.class, () -> messageService.save(messageItem));

        verify(messageRepository, times(3)).save(messageItem);
    }
}
