package com.unir.payments.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {

    FAILED("Fallido"),
    COMPLETED("Completado"),
    PENDING("Pendiente");

    private final String label;

    PaymentStatus(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static PaymentStatus fromValue(String value) {
        for (PaymentStatus type : values()) {
            if (type.label.equalsIgnoreCase(value)
                    || type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Estado invalido: " + value);
    }

}
