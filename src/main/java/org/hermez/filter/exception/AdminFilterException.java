package org.hermez.filter.exception;

public class AdminFilterException extends RuntimeException {
    public AdminFilterException(String message) {
        super(message);
    }

    public AdminFilterException(String message, Throwable cause) {
        super(message, cause);
    }
}