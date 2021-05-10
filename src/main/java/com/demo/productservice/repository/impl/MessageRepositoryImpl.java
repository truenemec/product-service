package com.demo.productservice.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.demo.productservice.domain.MessageItem;
import com.demo.productservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final Class<MessageItem> clazz = MessageItem.class;

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public PaginatedList<MessageItem> findBySeverity(String severity) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":severity", new AttributeValue().withS(severity));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("severity = :severity")
                .withExpressionAttributeValues(eav);
        return dynamoDBMapper.scan(MessageItem.class, scanExpression);
    }

    @Override
    public String save(MessageItem item) {
        dynamoDBMapper.save(item);
        return item.getGuid();
    }

    @Override
    public MessageItem findById(String id) {
        return dynamoDBMapper.load(clazz, id);
    }
}
