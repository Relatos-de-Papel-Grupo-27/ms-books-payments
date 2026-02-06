package com.unir.payments.service;

import com.unir.payments.controller.model.*;
import com.unir.payments.data.model.Payment;

import java.util.List;

public interface PaymentService {
	
//	Order createOrder(OrderRequest request);

	List<Payment> getPayments();

	PaymentResponse getPaymentById(Long id);

	PaymentResponse updatePayment(Long id, PaymentRequest request);

	PaymentResponse patchPayment(Long id, PaymentUpdateRequest request);

	PaymentResponseCreate createPayment(PaymentRequestCreate request);


}
