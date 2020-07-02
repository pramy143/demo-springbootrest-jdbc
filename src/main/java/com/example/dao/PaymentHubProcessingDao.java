package com.example.dao;

import com.example.model.Payment;

import java.util.List;

public interface PaymentHubProcessingDao {

    public List<Payment> fetchAllPayments();

    public Payment fetchPaymentById(final Long paymentId);

    public Payment updatePaymentById(final Payment payment);

    public Payment createPayment(Payment paymentEntry);

    public Payment deletePaymentById(final Payment payment);

}