package com.unir.payments.service;

import com.unir.payments.controller.model.PaymentResponse;
import com.unir.payments.data.model.Payment;

import java.util.List;

public interface PaymentService {
	
//	Order createOrder(OrderRequest request);

	List<Payment> getPayments();

	PaymentResponse getPaymentById(Long id);
}
