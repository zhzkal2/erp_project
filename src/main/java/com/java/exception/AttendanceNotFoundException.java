package com.java.exception;

public class AttendanceNotFoundException extends RuntimeException {

    private final int errorCode;

    public AttendanceNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
