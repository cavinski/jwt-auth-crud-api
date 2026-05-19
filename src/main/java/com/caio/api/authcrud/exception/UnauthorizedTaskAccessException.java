package com.caio.api.authcrud.exception;

public class UnauthorizedTaskAccessException extends RuntimeException{
    
    public UnauthorizedTaskAccessException(String message) {
        super(message);
    }
}
