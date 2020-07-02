package com.example.controller.validator;

import com.example.config.msgresolver.MessageHandler;
import com.example.exception.AppBusinessException;
import com.example.exception.util.ErrorEnumeration;
import com.example.model.Payment;
import com.example.model.enums.PaymentType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentHubRequestValidator {

    @Autowired
    private MessageHandler messageHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentHubRequestValidator.class);

    public void validate (Payment payment){
        if(payment.getPaymentType() != null && payment.getPaymentType().notIn(PaymentType.paymentTypes())) {
            throw new AppBusinessException(MessageHandler.toLocale(ErrorEnumeration.INVALID_ENUM));
        }

        if(payment.getPaymentType() != null && payment.getPaymentType().notIn(PaymentType.paymentTypes())) {
            throw new AppBusinessException(MessageHandler.toLocale(ErrorEnumeration.INVALID_ENUM));
        }
    }
}
