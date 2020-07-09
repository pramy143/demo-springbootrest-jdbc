package com.example.camel.router;

import com.example.camel.processor.PaymentFileProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

@Component
public class PaymentParsingRoute extends RouteBuilder {

	DataFormat paymentDataformat = new BeanIODataFormat("mapping/payment-mapping.xml", "paymentStream");

	@Override
	public void configure() throws Exception {
		from("file:C:/payment?noop=true&delay=5000")
			.routeId("runningCamelBeanIo")
			.autoStartup(true)
		.unmarshal(paymentDataformat)
		.process(new PaymentFileProcessor())
		.end();

	}

	/* This be low snippet can be used for running this route standalone*/
	/*public static void main(String[] args) {
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new PaymentParsingRoute());

			camelContext.start();

			Thread.sleep(10000);
			camelContext.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}