package com.example.service;

import com.example.dao.PaymentHubProcessingDao;
import com.example.model.Payment;
import com.example.model.enums.PaymentStatus;
import com.example.model.enums.PaymentType;
import com.example.service.decorator.PaymentHubProcessingDecorator;
import com.example.service.impl.PaymentHubProcessingServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PaymentHubProcessingServiceTest {

    private Payment payment;

    @Mock
    private PaymentHubProcessingDao paymentHubProcessingDao;

    @InjectMocks
    private PaymentHubProcessingServiceImpl paymentHubProcessingService;

    @Mock
    PaymentHubProcessingDecorator paymentHubProcessingDecorator;

    @BeforeEach
    public void init() {
        payment = new Payment.Builder()
            .paymentType("SEPA")
            .paymentStatus("COMPLETED")
            .build();
    }

    @Test
    @DisplayName("Service: Fetch payments success")
    void testFetchPaymentsSuccess() {
        List<Payment> payments = Collections.singletonList(payment);
        given(paymentHubProcessingDao.fetchAllPayments()).willReturn(payments);

        List<Payment> result = paymentHubProcessingService.fetchAllPayments();

        assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Service: Fetch payment by id success")
    void testFetchPaymentByIdSuccess() {
        final Long paymentId = 1L;
        given(paymentHubProcessingDao.fetchPaymentById(paymentId)).willReturn(payment);

        Payment result = paymentHubProcessingService.fetchPaymentById(paymentId);

        assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("Service: Create payment success")
    void testCreatePaymentSuccess() {
        given(paymentHubProcessingDao.createPayment(payment)).willReturn(payment);

        Payment result = paymentHubProcessingService.createPayment(payment);

        assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("Service: Update payment success")
    void testUpdatePaymentSuccess() {
        given(paymentHubProcessingDao.updatePaymentById(payment)).willReturn(payment);

        Payment result = paymentHubProcessingService.updatePayment(payment);

        assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("Service: Delete payment success")
    void testDeletePaymentSuccess() {
        given(paymentHubProcessingDao.deletePaymentById(payment)).willReturn(payment);

        Payment result = paymentHubProcessingService.deletePayment(payment);

        assertThat(result).isNotNull();

    }
}
