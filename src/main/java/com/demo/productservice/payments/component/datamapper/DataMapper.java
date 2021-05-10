package com.demo.productservice.payments.component.datamapper;

import java.util.List;
import java.util.Optional;

public interface DataMapper {
    Optional<DataMapperResult> map(String line, MapperContext context);
    List<DataMapperResult> generateFooter(MapperContext context);
}
