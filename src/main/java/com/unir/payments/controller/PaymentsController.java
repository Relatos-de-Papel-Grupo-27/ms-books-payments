package com.unir.payments.controller;
import com.unir.payments.controller.model.PaymentResponse;
import com.unir.payments.data.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.payments.service.PaymentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@Slf4j
public class PaymentsController {

    private final PaymentService service;

    public PaymentsController(PaymentService service) {
        this.service = service;
    }


    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments() {
        return ResponseEntity.ok(service.getPayments());
    }

    // GET /payments/{id}
    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaymentById(id));
    }

}
