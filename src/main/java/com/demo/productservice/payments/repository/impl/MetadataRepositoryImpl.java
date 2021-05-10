package com.demo.productservice.payments.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.demo.productservice.payments.model.Metadata;
import com.demo.productservice.payments.repository.MetadataRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class MetadataRepositoryImpl extends AbstractDynamoDBRepositoryImpl<Metadata>
        implements MetadataRepository {
    private final DynamoDBMapper dynamoDBMapper;
    @Override
    Class<Metadata> getModelClass() {
        return Metadata.class;
    }
}
