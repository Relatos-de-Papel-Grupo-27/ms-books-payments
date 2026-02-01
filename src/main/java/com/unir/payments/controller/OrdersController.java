package com.unir.payments.controller;

import com.unir.payments.data.model.Order;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.payments.controller.model.OrderRequest;
import com.unir.payments.service.OrdersService;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersService service; //Inyeccion por constructor mediante @RequiredArgsConstructor. Y, también es inyección por interfaz.

//    @PostMapping("/orders")
//    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest request) { //Se valida con Jakarta Validation API
//
//        log.info("Creating order...");
//        Order created = service.createOrder(request);
//
//        if (created != null) {
//            return ResponseEntity.ok(created);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {

        return ResponseEntity.ok(service.getOrders());

    }

//    @GetMapping("/orders/{id}")
//    public ResponseEntity<Order> getOrder(@PathVariable String id) {
//
//        Order order = service.getOrder(id);
//        if (order != null) {
//            return ResponseEntity.ok(order);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
