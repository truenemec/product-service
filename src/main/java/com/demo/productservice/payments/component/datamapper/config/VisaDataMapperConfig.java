package com.demo.productservice.payments.component.datamapper.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("visa-map-config")
@Getter
@Setter
public class VisaDataMapperConfig {
    @Data
    public static class Column{
        private String columnName;
    }
    @Data
    public static class ColumnMap{
        private Column title;
        private Column credit;
        private Column debit;
    }
    private long headerColumnNumber;
    @NotEmpty
    private String fileDelimiter = ",";
    private ColumnMap columnMap;
}
