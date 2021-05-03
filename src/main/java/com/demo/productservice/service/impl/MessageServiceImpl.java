package com.demo.productservice.service.impl;

import com.demo.productservice.domain.MessageItem;
import com.demo.productservice.repository.MessageRepository;
import com.demo.productservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;

    @Override
    @Retryable(value = RuntimeException.class)
    public String save(MessageItem message) {
        return messageRepository.save(message);
    }

    @Override
    public MessageItem findById(String guid) {
        return messageRepository.findById(guid);
    }

    @Override
    public Page<MessageItem> findBySeverity(String severity) {
        List<MessageItem>  result = new ArrayList<>(messageRepository.findBySeverity(severity));
        return new PageImpl<>(result);
    }
}
