package com.workshop.bootdockerworkshop.exception;

import java.util.function.Supplier;

public class CounteryCodeNotFoundException extends RuntimeException {

    public CounteryCodeNotFoundException() {
        super("INVALID_COUNTRY_CODE");
    }
}
