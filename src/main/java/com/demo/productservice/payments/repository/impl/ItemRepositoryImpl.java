package com.demo.productservice.payments.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.demo.productservice.payments.model.Item;
import com.demo.productservice.payments.repository.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Getter
public class ItemRepositoryImpl extends AbstractDynamoDBRepositoryImpl<Item> implements ItemRepository {
    private final DynamoDBMapper dynamoDBMapper;
    @Override
    public Optional<Item> getByFileNameLatest(String fileName) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":file_name", new AttributeValue().withS(fileName));

        DynamoDBQueryExpression<Item> queryExpression = new DynamoDBQueryExpression<Item>()
                .withKeyConditionExpression("file_name = :file_name")
                .withScanIndexForward(false)
                .withExpressionAttributeValues(eav);
        PaginatedQueryList<Item> page = dynamoDBMapper.query(Item.class, queryExpression);
        return page.stream().findFirst();
    }

    @Override
    Class<Item> getModelClass() {
        return Item.class;
    }
}
