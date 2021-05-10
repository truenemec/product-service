package com.demo.productservice.payments.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@DynamoDBDocument
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    @DynamoDBAttribute(attributeName = "proceed_bytes")
    private long proceedBytes;
    @DynamoDBAttribute(attributeName = "proceed_lines")
    private long proceedLines;
    @DynamoDBAttribute(attributeName = "current_line")
    private long currentLine;
    @DynamoDBAttribute(attributeName = "file_name")
    private String fileName;
    @DynamoDBAttribute(attributeName = "aggregation")
    private Map<String, String> aggregation = new HashMap<>();
}
