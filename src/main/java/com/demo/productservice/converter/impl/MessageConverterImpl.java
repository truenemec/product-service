package com.demo.productservice.converter.impl;

import com.demo.productservice.converter.MessageConverter;
import com.demo.productservice.domain.MessageItem;
import com.demo.productservice.dto.MessageDto;
import org.springframework.stereotype.Component;

@Component
public class MessageConverterImpl implements MessageConverter {
    @Override
    public MessageItem convertToDomain(MessageDto messageDto) {
        return MessageItem.builder()
                .message(messageDto.getMessage())
                .guid(messageDto.getGuid())
                .severity(messageDto.getSeverity())
                .build();
    }

    @Override
    public MessageDto convertToDto(MessageItem item) {
        return MessageDto.builder()
                .message(item.getMessage())
                .guid(item.getGuid())
                .severity(item.getSeverity())
                .build();
    }
}
