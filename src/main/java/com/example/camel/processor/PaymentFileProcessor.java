package com.example.camel.processor;

import com.example.model.Payment;
import com.example.model.Payments;
import com.example.service.PaymentHubProcessingService;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class PaymentFileProcessor implements Processor {

	@Autowired
	private PaymentHubProcessingService paymentHubProcessingService;

	public void process(Exchange exchange) throws Exception {

		List<List<Payment>> data = (List<List<Payment>>) exchange.getIn().getBody();
		Payments payments = (Payments) data.get(1);
		System.out.println("size is :" + data.get(2));

		if(!CollectionUtils.isEmpty(payments.getPayment())) {
			// iterate over each line
			for( Payment payment : payments.getPayment()) {
				//paymentHubProcessingService.createPayment(payment);
			}
		}

	}
}