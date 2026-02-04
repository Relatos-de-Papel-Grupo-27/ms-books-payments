package com.unir.payments.service;

import com.unir.payments.controller.model.PaymentRequest;
import com.unir.payments.controller.model.PaymentResponse;
import com.unir.payments.controller.model.PaymentUpdateRequest;
import com.unir.payments.data.PaymentJpaRepository;
import com.unir.payments.data.model.Payment;
import com.unir.payments.facade.OrdersFacade;
import com.unir.payments.facade.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            .paymentMethod(payment.getPaymentMethod())
            .provider(payment.getProvider())
            .order(order)
            .build();
  }

  @Override
  public PaymentResponse updatePayment(Long id, PaymentRequest request) {
    Payment payment = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment no encontrado"));

    payment.setOrderId(request.getOrderId());
    payment.setAmount(request.getAmount());
    payment.setStatus(request.getStatus());
    payment.setPaymentMethod(request.getPaymentMethod());
    payment.setProvider(request.getProvider());
    payment.setPaymentDate(LocalDateTime.now());

    Payment savedPayment = repository.save(payment);
    // Intentar obtener la orden, pero continuar aunque falle
    Order order = ordersFacade.getOrderById(savedPayment.getOrderId());

    return PaymentResponse.builder()
            .id(savedPayment.getId())
            .amount(savedPayment.getAmount())
            .status(savedPayment.getStatus())
            .PaymentDate(savedPayment.getPaymentDate())
            .paymentMethod(savedPayment.getPaymentMethod())
            .provider(savedPayment.getProvider())
            .order(order)
            .build();
  }

  @Override
  public PaymentResponse patchPayment(Long id, PaymentUpdateRequest request) {
    Payment payment = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment no encontrado"));

    if (request.getOrderId() != null) {
      payment.setOrderId(request.getOrderId());
    }
    if (request.getAmount() != null) {
      payment.setAmount(request.getAmount());
    }
    if (request.getStatus() != null) {
      payment.setStatus(request.getStatus());
    }
    if (request.getPaymentMethod() != null) {
      payment.setPaymentMethod(request.getPaymentMethod());
    }
    if (request.getProvider() != null) {
      payment.setProvider(request.getProvider());
    }

    Payment savedPayment = repository.save(payment);
    // Intentar obtener la orden, pero continuar aunque falle
    Order order = ordersFacade.getOrderById(savedPayment.getOrderId());

    return PaymentResponse.builder()
            .id(savedPayment.getId())
            .amount(savedPayment.getAmount())
            .status(savedPayment.getStatus())
            .PaymentDate(savedPayment.getPaymentDate())
            .paymentMethod(savedPayment.getPaymentMethod())
            .provider(savedPayment.getProvider())
            .order(order)
            .build();
  }
}
