package com.example.exception;

import com.example.exception.util.ErrorMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppCustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppCustomExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
        LOGGER.info("Generic ", ex);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AppBusinessException.class})
    public ResponseEntity<Object> handleCustomBException(AppBusinessException ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
        LOGGER.error("Business Exception ", ex);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, AppTechnicalException.class})
    public ResponseEntity<Object> handleCustomTException(NullPointerException ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

        LOGGER.info("Technical Exception ", ex);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
