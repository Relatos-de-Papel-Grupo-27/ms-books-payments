package com.unir.payments.controller.model;

import com.unir.payments.data.model.PaymentMethod;
import com.unir.payments.data.model.PaymentStatus;
import com.unir.payments.data.model.Providers;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Modelo para actualización parcial (PATCH) - Todos los campos son opcionales, solo envía los que deseas actualizar")
public class PaymentUpdateRequest {

    @Schema(description = "ID de la orden asociada al pago (opcional)", example = "1")
    private Long orderId;

    @Positive(message = "amount debe ser positivo")
    @Schema(description = "Monto del pago (opcional)", example = "150.00")
    private BigDecimal amount;

    @Schema(description = "Estado del pago (opcional) - COMPLETED, PENDING, FAILED", example = "COMPLETED")
    private PaymentStatus status;

    @Schema(description = "Método de pago (opcional) - TARJETA_CREDITO, TARJETA_DEBITO", example = "TARJETA_CREDITO")
    private PaymentMethod paymentMethod;

    @Schema(description = "Proveedor/Pasarela de pago (opcional) - ej: Stripe, PayPal, Mercado Pago", example = "Stripe")
    private Providers provider;
}
