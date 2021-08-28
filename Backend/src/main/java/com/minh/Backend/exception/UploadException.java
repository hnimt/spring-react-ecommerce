package com.minh.Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UploadException extends RuntimeException{
    private static final long serialVersionUID = 2L;

    public UploadException(String message) {
        super(message);
    }
}
