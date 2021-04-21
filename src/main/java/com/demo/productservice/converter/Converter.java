package com.demo.productservice.converter;

public interface Converter <DTO, DOMAIN>  {
    DOMAIN convertToDomain(DTO dto);
    DTO convertToDto(DOMAIN domain);
}
