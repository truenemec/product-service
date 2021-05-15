package com.demo.productservice.payments.component.datamapper.config;

import com.demo.productservice.payments.component.datamapper.config.common.Aggregated;
import com.demo.productservice.payments.component.datamapper.config.common.ColumnMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("visa-map-config")
@Getter
@Setter
public class VisaDataMapperConfig {

    private long headerColumnNumber;
    @NotEmpty
    private String fileDelimiter = ",";
    private ColumnMap columnMap;
    private Aggregated aggregated;

}
