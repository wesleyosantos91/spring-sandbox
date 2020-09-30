package io.wesleyosantos91.springsandbox.exception.core;

public class FallbackMethodException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FallbackMethodException(String message) {
        super(message);
    }
}