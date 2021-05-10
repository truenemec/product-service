package com.demo.productservice.payments.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.demo.productservice.payments.model.ItemAggregated;
import com.demo.productservice.payments.repository.ItemAggregatedRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class ItemAggregatedRepositoryImpl extends AbstractDynamoDBRepositoryImpl<ItemAggregated>
        implements ItemAggregatedRepository {
    private final DynamoDBMapper dynamoDBMapper;

    @Override
    Class<ItemAggregated> getModelClass() {
        return ItemAggregated.class;
    }
}
