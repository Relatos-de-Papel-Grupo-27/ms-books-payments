package com.unir.payments.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Providers {

    PAYPAL("PayPal"),
    MERCADO_PAGO("Mercado Pago"),
    STRIPE("Stripe");

    private final String label;

    Providers(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Providers fromValue(String value) {
        for (Providers type : values()) {
            if (type.label.equalsIgnoreCase(value)
                    || type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Proveedor invalido: " + value);
    }

}
