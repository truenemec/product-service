package com.demo.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class MessageDto {
    private String guid;
    private String message;
    private String severity;
    private Map<String, Object> data;
}
