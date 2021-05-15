package com.demo.productservice.payments.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

import java.util.Optional;

public interface DynamoDBRepository<T> {
    void save(T value);
    Optional<T> getByKey(Object key);
    PaginatedScanList<T> getAll();
}
