package com.java.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    private final int errorCode;

    public EmailAlreadyExistsException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
