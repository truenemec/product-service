package com.demo.productservice.payments.repository;

import java.util.Optional;

public interface DynamoDBRepository<T> {
    void save(T value);
    Optional<T> getByKey(Object key);
}
