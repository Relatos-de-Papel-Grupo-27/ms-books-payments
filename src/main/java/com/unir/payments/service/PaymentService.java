package com.unir.payments.service;

import com.unir.payments.data.model.Payment;

import java.util.List;

public interface PaymentService {
	
//	Order createOrder(OrderRequest request);

	List<Payment> getPayments();

	Payment getPaymentById(Long id);
}
