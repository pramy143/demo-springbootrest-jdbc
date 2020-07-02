package com.example.exception;

public class AppBusinessException extends RuntimeException {

    private static final long serialVersionUID = 5686002958837120448L;

    public AppBusinessException(String errorMessage) {
        super(errorMessage);
    }

    public AppBusinessException(String errorMessage, String message) {
        super(errorMessage.concat(" ").concat(message));

    }

}
