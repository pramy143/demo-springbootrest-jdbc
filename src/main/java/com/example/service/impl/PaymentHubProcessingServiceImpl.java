package com.example.service.impl;

import com.example.dao.PaymentHubProcessingDao;
import com.example.model.Payment;
import com.example.config.msgresolver.MessageHandler;
import com.example.service.PaymentHubProcessingService;
import com.example.service.decorator.PaymentHubProcessingDecorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentHubProcessingServiceImpl implements PaymentHubProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentHubProcessingServiceImpl.class);

    @Autowired
    private PaymentHubProcessingDao paymentHubProcessingDao;

    @Autowired
    private MessageHandler messageHandler;

    @Autowired PaymentHubProcessingDecorator paymentHubProcessingDecorator;

    @Override
    public List<Payment> fetchAllPayments() {
        return paymentHubProcessingDao.fetchAllPayments();
    }

    @Override
    public Payment fetchPaymentById(final Long paymentId) {
        return paymentHubProcessingDao.fetchPaymentById(paymentId);
    }

    @Override
    public Payment createPayment(final Payment payment) {
        paymentHubProcessingDecorator.decorate(payment);
        return paymentHubProcessingDao.createPayment(payment);
    }

    @Override
    public Payment updatePayment(final Payment payment) {
        return paymentHubProcessingDao.updatePaymentById(payment);
    }

    @Override
    public Payment deletePayment(final Payment payment) {
        return paymentHubProcessingDao.deletePaymentById(payment);
    }

}
