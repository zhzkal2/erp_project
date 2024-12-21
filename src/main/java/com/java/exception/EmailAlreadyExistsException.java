package com.java.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
