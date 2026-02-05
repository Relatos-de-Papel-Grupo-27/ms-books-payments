package com.unir.payments.controller.model;

import com.unir.payments.data.model.PaymentMethod;
import com.unir.payments.data.model.PaymentStatus;
import com.unir.payments.data.model.Providers;
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
public class PaymentRequestCreate {

    @NotNull(message = "orderId es requerido")
    @Schema(description = "ID de la orden asociada al pago", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long orderId;

    @NotNull(message = "amount es requerido")
    @Positive(message = "amount debe ser positivo")
    @Schema(description = "Monto del pago", requiredMode = Schema.RequiredMode.REQUIRED, example = "150.00")
    private BigDecimal amount;

    @NotNull(message = "paymentMethod es requerido")
    @Schema(description = "Método de pago (Tarjeta Credito, Tarjeta Debito/Corriente)", requiredMode = Schema.RequiredMode.REQUIRED, example = "Tarjeta Credito")
    private PaymentMethod paymentMethod;

    @NotNull(message = "provider es requerido")
    @Schema(description = "Proveedor/Pasarela de pago (ej: Stripe, PayPal, Mercado Pago)", requiredMode = Schema.RequiredMode.REQUIRED, example = "Stripe")
    private Providers provider;
}
