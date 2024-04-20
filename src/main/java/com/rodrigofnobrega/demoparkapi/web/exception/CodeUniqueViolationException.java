package com.rodrigofnobrega.demoparkapi.web.exception;

public class CodeUniqueViolationException extends RuntimeException {
    public CodeUniqueViolationException(String message) {
        super(message);
    }
}
