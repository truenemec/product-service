package com.demo.productservice.payments.component.datamapper.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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
    @Data
    public static class Aggregated{
        private Rule rule1;
    }

    @Data
    public static class Rule{
        private List<ColumnRule> columns;
    }

    @Data
    public static class ColumnRule{
        private String name;
        private String type;
        private List<String> values;
    }

    private long headerColumnNumber;
    @NotEmpty
    private String fileDelimiter = ",";
    private ColumnMap columnMap;
    private Aggregated aggregated;

}
