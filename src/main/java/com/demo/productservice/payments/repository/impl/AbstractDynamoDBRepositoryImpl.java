package com.demo.productservice.payments.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractDynamoDBRepositoryImpl<T> {
    abstract DynamoDBMapper getDynamoDBMapper();
    abstract Class<T> getModelClass();
    public void save(T value) {
        getDynamoDBMapper().save(value);
    }

    public Optional<T> getByKey(Object key) {
        T result = getDynamoDBMapper().load(getModelClass(), key);
        return Optional.ofNullable(result);
    }
}
