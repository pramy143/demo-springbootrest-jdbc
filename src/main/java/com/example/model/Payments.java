package com.example.model;

import java.io.Serializable;
import java.util.List;

public class Payments implements Serializable {
  
	 private static final long serialVersionUID = 1L;
	 private List<Payment> payment = null;

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}
 
  
}