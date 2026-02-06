package com.unir.payments.controller;
import com.unir.payments.controller.model.PaymentRequest;
import com.unir.payments.controller.model.PaymentRequestCreate;
import com.unir.payments.controller.model.PaymentResponse;
import com.unir.payments.controller.model.PaymentUpdateRequest;
import com.unir.payments.data.model.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.payments.service.PaymentService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@Tag(name = "Payments", description = "API para la gestión de pagos")
public class PaymentsController {

    private final PaymentService service;

    public PaymentsController(PaymentService service) {
        this.service = service;
    }


    @Operation(summary = "Get para obtener lista de pagos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pagos retornada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments() {
        return ResponseEntity.ok(service.getPayments());
    }


    @Operation(summary = "Obtener pago por Id.",
             description = "** Regresa imnformacion del pago y de la orden asociada al pago.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    // GET /payments/{id}
    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaymentById(id));
    }



    // PUT /payments/{id}
    @PutMapping("/payments/{id}")
    @Operation(
            summary = "Actualizar pago completo (PUT)",
            description = "**PUT - Actualización completa**: Reemplaza completamente el recurso de pago. " +
                    "Requiere enviar TODOS los campos obligatorios (orderId, amount, status). " +
                    "Los campos no enviados serán reemplazados con los valores proporcionados. " +
                    "Úsalo cuando quieras actualizar todos los campos del pago de una vez."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<PaymentResponse> updatePayment(
            @Parameter(description = "ID del pago a actualizar", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Datos completos del pago (todos los campos son obligatorios)", required = true)
            @Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(service.updatePayment(id, request));
    }

    // PATCH /payments/{id}
    @PatchMapping("/payments/{id}")
    @Operation(
            summary = "Actualizar pago parcialmente (PATCH)",
            description = "**PATCH - Actualización parcial**: Actualiza solo los campos específicos del pago. " +
                    "Solo necesitas enviar los campos que deseas modificar (orderId, amount, status). " +
                    "Los campos no enviados permanecerán sin cambios. " +
                    "Úsalo cuando solo quieras actualizar uno o algunos campos específicos del pago."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<PaymentResponse> patchPayment(
            @Parameter(description = "ID del pago a actualizar", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Datos parciales del pago (solo envía los campos que deseas actualizar)", required = true)
            @Valid @RequestBody PaymentUpdateRequest request) {
        return ResponseEntity.ok(service.patchPayment(id, request));
    }


    @Operation(
            summary = "Crear Pago",
            description = "Para la creacion del pago la orden debe estar en estado VERIFIED"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pago creado"),
            @ApiResponse(responseCode = "400", description = "Estado de la oden invalido"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody PaymentRequestCreate request) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPayment(request));

        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "INVALID_ORDER_STATE",
                            "message", ex.getMessage()
                    ));

        } catch (NoSuchElementException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "ORDER_NOT_FOUND",
                            "message", ex.getMessage()
                    ));

        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "INTERNAL_ERROR",
                            "message", "Unexpected error"
                    ));
        }
    }


}
