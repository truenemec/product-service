package com.demo.productservice.payments.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@DynamoDBTable(tableName = "item")
@Data
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Item extends ItemBase {
    @DynamoDBRangeKey(attributeName = "current_line")
    public long getCurrentLine(){
        return getStatistic().getCurrentLine();
    }
    @DynamoDBHashKey(attributeName="file_name")
    public String getFileName(){
        return getStatistic().getFileName();
    }
    public void setFileName(String name){
        getStatistic().setFileName(name);
    }
    public void setCurrentLine(long currentLine){
        getStatistic().setCurrentLine(currentLine);
    }
}
