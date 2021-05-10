package com.demo.productservice.payments.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.demo.productservice.payments.component.datamapper.StatisticAware;
import lombok.Builder;
import lombok.Data;

@DynamoDBTable(tableName = "item_aggregated")
@Data
@Builder
public class ItemAggregated implements StatisticAware {
    @DynamoDBHashKey(attributeName="file_name")
    private String fileName;
    @DynamoDBRangeKey(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "metadata")
    private Statistic statistic;
}
