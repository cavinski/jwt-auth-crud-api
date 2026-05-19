package com.caio.api.authcrud.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
