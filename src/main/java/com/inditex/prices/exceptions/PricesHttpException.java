package com.inditex.prices.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PricesHttpException extends RuntimeException {

    HttpStatus statusCode;
    Map<String, String> errors;

    private PricesHttpException(String message, HttpStatus statusCode, Map<String, String> errors) {
        super(message);
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public static PricesHttpException notFound(String message) {
        return new PricesHttpException(message, NOT_FOUND, new HashMap<>());
    }
}
