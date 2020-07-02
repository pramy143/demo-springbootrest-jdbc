package com.example.exception;

public class AppTechnicalException extends Exception {

    private static final long serialVersionUID = 5686002958837120448L;

    private String customErrorMessage;

    public AppTechnicalException(String message) {
        super(message);
    }

    public String getCustomErrorMessage() {
        return customErrorMessage;
    }

    public void setCustomErrorMessage(String customErrorMessage) {
        this.customErrorMessage = customErrorMessage;
    }
    
    
}
