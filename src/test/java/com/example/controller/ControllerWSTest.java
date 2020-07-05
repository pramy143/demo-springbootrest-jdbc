package com.evry.fs.payment.hub.controller;

import com.example.controller.PaymentHubController;
import com.example.controller.validator.PaymentHubRequestValidator;
import com.example.service.PaymentHubProcessingService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PaymentHubController.class)
public class ControllerWSTest {
    @Autowired
    private PaymentHubController controller;

    @MockBean
    private PaymentHubRequestValidator paymentHubRequestValidator;

    @MockBean
    private PaymentHubProcessingService paymentHubProcessingService;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
