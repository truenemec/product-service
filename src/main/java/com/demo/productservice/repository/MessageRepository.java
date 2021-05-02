package com.demo.productservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.demo.productservice.domain.MessageItem;

public interface MessageRepository {
    PaginatedList<MessageItem> findBySeverity(String severity);
    String save(MessageItem item);
    MessageItem findById(String id);
}
