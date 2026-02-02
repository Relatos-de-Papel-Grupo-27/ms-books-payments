package com.unir.payments.service;

import com.unir.payments.controller.model.PaymentResponse;
import com.unir.payments.data.PaymentJpaRepository;
import com.unir.payments.data.model.Payment;
import com.unir.payments.facade.OrdersFacade;
import com.unir.payments.facade.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final PaymentJpaRepository repository;
  private final OrdersFacade ordersFacade;

  public PaymentServiceImpl(PaymentJpaRepository repository,
                            OrdersFacade ordersFacade) {
    this.repository = repository;
    this.ordersFacade = ordersFacade;
  }

  @Override
  public List<Payment> getPayments() {
    return repository.findAll();
  }

  @Override
  public PaymentResponse getPaymentById(Long id) {

    Payment payment = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment no encontrado"));

    Order order = ordersFacade.getOrderById(payment.getOrderId());

    return PaymentResponse.builder()
            .id(payment.getId())
            .amount(payment.getAmount())
            .status(payment.getStatus())
            .PaymentDate(payment.getPaymentDate())
            .order(order)
            .build();
  }
}
