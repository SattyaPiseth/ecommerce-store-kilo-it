package com.example.demo.exception.httpstatus;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

    private final String message;
    @Getter
    private Object data;

    public BadRequestException(String message, String messageKey, Object data) {
        this.message = message;
        this.data = data;
    }
    public BadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
