package org.example.exception;

public class TareaNoEncontradaException extends RuntimeException {
    public TareaNoEncontradaException(String message) {
        super(message);
    }
}