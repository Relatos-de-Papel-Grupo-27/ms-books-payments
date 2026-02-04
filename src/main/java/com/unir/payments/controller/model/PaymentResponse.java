package com.unir.payments.controller.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.unir.payments.data.model.PaymentMethod;
import com.unir.payments.facade.model.Order;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "amount",
        "status",
        "PaymentDate",
        "paymentMethod",
        "provider",
        "order"
})

@Data
@Builder
public class PaymentResponse {

    private Long id;
    private BigDecimal amount;
    private String status;
    private LocalDateTime PaymentDate;
    private PaymentMethod paymentMethod;
    private String provider;
    private Order order;
}
