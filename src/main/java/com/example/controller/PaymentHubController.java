package com.example.controller;

import com.example.config.msgresolver.MessageHandler;
import com.example.controller.validator.PaymentHubRequestValidator;
import com.example.model.Payment;
import com.example.service.PaymentHubProcessingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

import java.util.List;

@RestController
@RequestMapping("/payments-hub")
public class PaymentHubController {

    @Autowired
    private PaymentHubProcessingService paymentHubProcessingService;

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private PaymentHubRequestValidator paymentHubRequestValidator;

    @GetMapping(path = "/fetchPayments")
    public List<Payment> fetchPayments(){

        return paymentHubProcessingService.fetchAllPayments();
    }

    @GetMapping(path = "/{paymentId}")
    public Payment fetchPaymentById(@PathVariable @Min(1) Long paymentId){
        return paymentHubProcessingService.fetchPaymentById(paymentId);
    }

    @PostMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(@RequestBody Payment payment) {
        paymentHubRequestValidator.validate(payment);
        return paymentHubProcessingService.createPayment(payment);
    }

    @PutMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Payment updatePayment(@RequestBody Payment payment ) {

        return paymentHubProcessingService.updatePayment(payment);
    }

    @DeleteMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Payment deletePayment(@RequestBody Payment payment) {
        return paymentHubProcessingService.deletePayment(payment);
    }

}
