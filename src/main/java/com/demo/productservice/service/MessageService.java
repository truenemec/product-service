package com.demo.productservice.service;

import com.demo.productservice.domain.MessageItem;
import org.springframework.data.domain.Page;

public interface MessageService {
    String save(MessageItem message);
    MessageItem findById(String guid);
    Page<MessageItem> findBySeverity(String severity);
}
