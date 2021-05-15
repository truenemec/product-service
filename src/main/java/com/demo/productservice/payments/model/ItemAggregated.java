package com.demo.productservice.payments.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@DynamoDBTable(tableName = "item_aggregated")
@SuperBuilder
public class ItemAggregated extends ItemBase {
    @Data
    @RequiredArgsConstructor
    public static class Key{
        private final String fileName;
        private final String title;

    }
    public static Key getKey(ItemAggregated itemAggregated){
        return new Key(itemAggregated.getFileName(), itemAggregated.getTitle());
    }

    @DynamoDBHashKey(attributeName="file_name")
    public String getFileName(){
        return super.getFileName();
    }
    @DynamoDBRangeKey(attributeName = "title")
    public String getTitle(){
        return super.getTitle();
    }
    public ItemAggregated add(ItemAggregated itemAggregated){
        return ItemAggregated.builder()
                .title(this.getTitle())
                .statistic(this.getStatistic())
                .credit(this.getCredit().add(itemAggregated.getCredit()))
                .debit(this.getDebit().add(itemAggregated.getDebit()))
                .build();
    }

}
