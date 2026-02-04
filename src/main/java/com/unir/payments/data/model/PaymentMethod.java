package com.unir.payments.data.model;

public enum PaymentMethod {
    TARJETA_CREDITO("Tarjeta Crédito"),
    TARJETA_DEBITO("Tarjeta Débito/Corriente");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
