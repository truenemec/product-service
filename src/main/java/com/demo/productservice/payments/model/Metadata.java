package com.demo.productservice.payments.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.demo.productservice.payments.component.datamapper.StatisticAware;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@DynamoDBTable(tableName = "metadata")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Metadata implements StatisticAware {
    @DynamoDBAttribute(attributeName = "statistic")
    private Statistic statistic = new Statistic();

    @DynamoDBAttribute(attributeName = "column_map")
    private Map<String, Integer> columnMap = new HashMap<>();

    @DynamoDBHashKey(attributeName="file_name")
    public String getFileName(){
        return statistic.getFileName();
    }
    public void setFileName(String name){
        statistic.setFileName(name);
    }
}
