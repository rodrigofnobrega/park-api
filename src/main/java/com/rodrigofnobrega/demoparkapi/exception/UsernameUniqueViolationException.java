package com.rodrigofnobrega.demoparkapi.exception;

import java.lang.RuntimeException;
public class UsernameUniqueViolationException extends RuntimeException {
    public UsernameUniqueViolationException(String message) {
        super(message);
    }
}
