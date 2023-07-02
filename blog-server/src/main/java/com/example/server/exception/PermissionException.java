package com.example.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class PermissionException extends RuntimeException {
    public PermissionException(String message) {
        super(message);
    }
}
