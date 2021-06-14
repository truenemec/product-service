package com.demo.productservice.rest;

import com.demo.productservice.converter.MessageConverter;
import com.demo.productservice.dto.MessageDto;

import com.demo.productservice.dto.Response;
import com.demo.productservice.exception.NotFoundException;
import com.demo.productservice.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/message", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MessageController {

    final MessageService messageService;

    final MessageConverter messageConverter;

    @GetMapping("{guid}")
    public MessageDto findById(@PathVariable("guid") String guid){
        return Optional.of(guid)
                .map(messageService::findById)
                .map(messageConverter::convertToDto)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("/severity/{severity}")
    public Page<MessageDto> findBySeverity(@PathVariable("severity") String severity){
        return messageService.findBySeverity(severity)
                .map(messageConverter::convertToDto);

    }

    @Data
    class SaveResponse{
        final String guid;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaveResponse saveMessage(@RequestBody MessageDto messageDto){
        return Optional.of(messageDto)
                .map(messageConverter::convertToDomain)
                .map(messageService::save)
                .map(SaveResponse::new)
                .orElseThrow(()-> new IllegalStateException("guid should be set"));
    }

    @PostMapping("transform")
    @ResponseStatus(HttpStatus.CREATED)
    public Response transform(@RequestBody MessageDto messageDto){
        return Response.builder()
                .guid(messageDto.getGuid())
                .map(messageDto.getData())
                .build();
    }
}
