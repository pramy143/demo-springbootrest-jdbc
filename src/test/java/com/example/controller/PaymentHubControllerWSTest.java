package com.example.controller;

import com.example.config.msgresolver.MessageHandler;
import com.example.controller.validator.PaymentHubRequestValidator;
import com.example.model.Payment;
import com.example.model.enums.PaymentStatus;
import com.example.model.enums.PaymentType;
import com.example.service.PaymentHubProcessingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PaymentHubController.class)
public class PaymentHubControllerWSTest {

    private Payment payment;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PaymentHubController paymentHubController;

    @MockBean
    private MessageHandler messageHandler;

    @MockBean
    private PaymentHubProcessingService paymentHubProcessingService;

    @MockBean
    private PaymentHubRequestValidator paymentHubRequestValidator;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        payment = new Payment.Builder()
            .paymentId(1L)
            .paymentType("SEPA")
            .paymentStatus("COMPLETED")
            .description("PaymentStatus.COMPLETED")
            .build();

    }


    @Test
    @DisplayName("Test for GET Mapping success: fetchPayments")
    void testFetchPaymentsReturns200() throws Exception {
        List<Payment> payments = Collections.singletonList(payment);
        given(paymentHubProcessingService.fetchAllPayments()).willReturn(payments);

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .get("/payments-hub/fetchPayments")
                    .accept(MediaType.APPLICATION_JSON)
            )
           .andDo(print())
           .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    @DisplayName("Test for GET Mapping by ID success: ")
    void testFetchPaymentIdReturns200() throws Exception {
        final Long paymentId = 1L;

        given(paymentHubProcessingService.fetchPaymentById(paymentId)).willReturn(payment);

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .get("/payments-hub/{paymentId}", paymentId)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId", is(Integer.parseInt(payment.getPaymentId().toString()))))
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentType", is(payment.getPaymentType().toString())));
    }

    @Test
    @DisplayName("Test for POST Mapping success: ")
    void testCreateNewPayment() throws Exception {
        given(paymentHubProcessingService.createPayment(any(Payment.class))).willAnswer((invocation) -> invocation.getArgument(0));

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/payments-hub")
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(payment))
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId", is(Integer.parseInt(payment.getPaymentId().toString()))))
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentType", is(payment.getPaymentType().toString())));
    }

    @Test
    @DisplayName("Test POST Mapping Bad Request: createPayment")
    void testFetchPaymentsReturns400() throws Exception {

        given(paymentHubProcessingService.createPayment(any(Payment.class))).willAnswer((invocation) -> invocation.getArgument(0));
        payment.setDescription(null);

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/payments-hub")
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(payment))
            )
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test for PUT Mapping success: ")
    void testUpdatePayment() throws Exception {

        given(paymentHubProcessingService.updatePayment(any(Payment.class))).willAnswer((invocation) -> invocation.getArgument(0));

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .put("/payments-hub")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(payment))
            )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId", is(Integer.parseInt(payment.getPaymentId().toString()))))
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentType", is(payment.getPaymentType().toString())));

    }

    @Test
    @DisplayName("Test for DELETE Mapping success: ")
    void testDeletePayment() throws Exception {

        given(paymentHubProcessingService.deletePayment(any(Payment.class))).willAnswer((invocation) -> invocation.getArgument(0));

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete("/payments-hub")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(payment))
            )
            .andExpect(status().isAccepted())
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId", is(Integer.parseInt(payment.getPaymentId().toString()))))
            .andExpect(MockMvcResultMatchers.jsonPath("$.paymentType", is(payment.getPaymentType().toString())));
    }

}
