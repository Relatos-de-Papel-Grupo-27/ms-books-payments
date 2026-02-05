package com.unir.payments.controller.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.unir.payments.data.model.PaymentMethod;
import com.unir.payments.data.model.PaymentStatus;
import com.unir.payments.data.model.Providers;
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
    private PaymentStatus status;
    private LocalDateTime PaymentDate;
    private PaymentMethod paymentMethod;
    private Providers provider;
    private Order order;
}
