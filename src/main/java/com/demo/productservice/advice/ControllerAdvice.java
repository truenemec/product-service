package com.demo.productservice.advice;

import com.demo.productservice.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error.Detail> details = ex.getAllErrors()
                .stream()
                .map(this::mapObjectError)
                .collect(Collectors.toList());
        Error error = Error.builder()
                .title("Validation failed")
                .status(status.value())
                .details(details)
                .build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(ex, null, headers, HttpStatus.NOT_FOUND, request);
    }

    Error.Detail mapObjectError(ObjectError error) {
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            return Error.Detail.builder()
                    .title("validation failed for field [" + fieldError.getField() + "] " +
                            "with value = [" + fieldError.getRejectedValue() + "]")
                    .detail(error.getDefaultMessage())
                    .build();
        } else {
            return Error.Detail.builder()
                    .title(error.getDefaultMessage())
                    .build();
        }
    }

}
