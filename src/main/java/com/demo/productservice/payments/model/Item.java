package com.demo.productservice.payments.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.demo.productservice.payments.component.datamapper.StatisticAware;
import lombok.*;

import java.math.BigDecimal;

@DynamoDBTable(tableName = "item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item implements StatisticAware {
    @DynamoDBAttribute(attributeName = "statistic")
    private Statistic statistic = new Statistic();
    @DynamoDBAttribute(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "debit")
    private BigDecimal debit;
    @DynamoDBAttribute(attributeName = "credit")
    private BigDecimal credit;

    @DynamoDBRangeKey(attributeName = "current_line")
    public long getCurrentLine(){
        return statistic.getCurrentLine();
    }
    @DynamoDBHashKey(attributeName="file_name")
    public String getFileName(){
        return statistic.getFileName();
    }
    public void setFileName(String name){
        statistic.setFileName(name);
    }
    public void setCurrentLine(long currentLine){
        statistic.setCurrentLine(currentLine);
    }
}
