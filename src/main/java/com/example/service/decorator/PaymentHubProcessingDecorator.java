package com.example.service.decorator;

import com.example.model.Payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentHubProcessingDecorator {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentHubProcessingDecorator.class);
    public void decorate(Payment payment) {
        //To Do for any payment object decoration or any mutation needs to be performed.

    }
}
