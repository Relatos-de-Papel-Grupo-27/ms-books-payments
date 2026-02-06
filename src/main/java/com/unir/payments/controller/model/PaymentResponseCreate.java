package com.unir.payments.controller.model;


import com.unir.payments.data.model.PaymentMethod;
import com.unir.payments.data.model.PaymentStatus;
import com.unir.payments.data.model.Providers;

import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
public class PaymentResponseCreate {

    private Long id;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime PaymentDate;
    private PaymentMethod paymentMethod;
    private Providers provider;
}
