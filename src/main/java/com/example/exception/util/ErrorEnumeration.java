package com.example.exception.util;

import javax.print.attribute.standard.Severity;

public enum ErrorEnumeration {

    /* Common */
    INVALID_ENUM(0, "err.missing.payment.fields", Severity.REPORT),
    SYSTEM_ERROR(1, "err.key.systemerror", Severity.ERROR);

    private final Severity severity;
    private final String messageKey;
    private final int errorCode;

    ErrorEnumeration(int errorCode, String messageKey, Severity severity) {
        this.errorCode = errorCode;
        this.messageKey = messageKey;
        this.severity = severity;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Severity getSeverity() {
        return severity;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
