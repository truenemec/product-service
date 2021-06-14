package com.demo.productservice.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class Response {
    String guid;

    @JsonIgnore
    Map<String, Object> map;

    @JsonAnyGetter
    //add to root elements
    public Map<String, Object> getMap() {
        return map;
    }

}
