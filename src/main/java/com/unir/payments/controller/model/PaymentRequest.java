package com.unir.payments.controller.model;

import com.unir.payments.data.model.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo para actualización completa (PUT) - Todos los campos son obligatorios")
public class PaymentRequest {

    @NotNull(message = "orderId es requerido")
    @Schema(description = "ID de la orden asociada al pago", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long orderId;

    @NotNull(message = "amount es requerido")
    @Positive(message = "amount debe ser positivo")
    @Schema(description = "Monto del pago", requiredMode = Schema.RequiredMode.REQUIRED, example = "150.00")
    private BigDecimal amount;

    @NotNull(message = "status es requerido")
    @Schema(description = "Estado del pago (COMPLETED, PENDING, FAILED)", requiredMode = Schema.RequiredMode.REQUIRED, example = "COMPLETED")
    private String status;

    @NotNull(message = "paymentMethod es requerido")
    @Schema(description = "Método de pago (TARJETA_CREDITO, TARJETA_DEBITO)", requiredMode = Schema.RequiredMode.REQUIRED, example = "TARJETA_CREDITO")
    private PaymentMethod paymentMethod;

    @NotNull(message = "provider es requerido")
    @Schema(description = "Proveedor/Pasarela de pago (ej: Stripe, PayPal, Mercado Pago)", requiredMode = Schema.RequiredMode.REQUIRED, example = "Stripe")
    private String provider;
}
