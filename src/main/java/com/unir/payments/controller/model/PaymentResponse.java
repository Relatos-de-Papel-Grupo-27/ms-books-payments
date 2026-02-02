package com.unir.payments.controller.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.unir.payments.facade.model.Order;

@JsonPropertyOrder({
        "id",
        "amount",
        "status",
        "PaymentDate",
        "order"
})

@Data
@Builder
public class PaymentResponse {

    private Long id;
    private BigDecimal amount;
    private String status;
    private LocalDateTime PaymentDate;
    private Order order;
}
