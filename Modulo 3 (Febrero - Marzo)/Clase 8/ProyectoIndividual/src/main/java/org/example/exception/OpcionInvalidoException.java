package org.example.exception;

public class OpcionInvalidoException extends RuntimeException {
    public OpcionInvalidoException(String message) {
        super(message);
    }
}