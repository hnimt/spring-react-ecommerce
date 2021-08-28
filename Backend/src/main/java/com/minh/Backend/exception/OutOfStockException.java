package com.minh.Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutOfStockException extends RuntimeException{
    private static final long serialVersionUID = 2L;

    public OutOfStockException(String message) {
        super(message);
    }
}
