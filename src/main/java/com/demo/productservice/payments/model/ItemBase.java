package com.demo.productservice.payments.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.demo.productservice.payments.component.datamapper.StatisticAware;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class ItemBase implements StatisticAware {
    @DynamoDBAttribute(attributeName = "statistic")
    private Statistic statistic = new Statistic();
    @DynamoDBAttribute(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "debit")
    private BigDecimal debit;
    @DynamoDBAttribute(attributeName = "credit")
    private BigDecimal credit;
    public long getCurrentLine(){
        return statistic.getCurrentLine();
    }
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
