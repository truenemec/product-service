package com.demo.productservice.advice;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Error {
    int status;
    String title;
    List<Detail> details;

    @Value
    @Builder
    public static class Detail {
        String title;
        String detail;
    }
}
