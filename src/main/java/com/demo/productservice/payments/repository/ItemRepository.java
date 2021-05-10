package com.demo.productservice.payments.repository;

import com.demo.productservice.payments.model.Item;

import java.util.Optional;

public interface ItemRepository extends DynamoDBRepository<Item> {
    Optional<Item> getByFileNameLatest(String fileName);
}
