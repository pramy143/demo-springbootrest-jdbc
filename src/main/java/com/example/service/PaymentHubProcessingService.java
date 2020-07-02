package com.example.service;

import com.example.model.Payment;

import java.util.List;

public interface PaymentHubProcessingService {
    List<Payment> fetchAllPayments();
    Payment fetchPaymentById(Long paymentId);
    Payment createPayment(final Payment payment);
    Payment updatePayment(final Payment payment);
    Payment deletePayment(final Payment payment);
}
