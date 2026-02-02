package com.unir.payments.facade.model;

import lombok.Data;

@Data
public class Account {

    private Long id;
    private String documentType;
    private String names;
    private String lastNames;
}