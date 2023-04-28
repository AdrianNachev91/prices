package com.inditex.prices.exceptions.advice;

import com.inditex.prices.exceptions.PricesHttpException;
import com.inditex.prices.web.PricesController;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice(assignableTypes = {PricesController.class})
public class ControllerExceptionAdvice {

    @ExceptionHandler(PricesHttpException.class)
    public ResponseEntity<PricesErrorResponse> handlePricesHttpException(PricesHttpException e) {

        var errorResponse = PricesErrorResponse.builder().message(e.getMessage()).build();
        for (var entrySet : e.getErrors().entrySet()) {
            errorResponse.addError(Error.builder()
                    .key(entrySet.getKey())
                    .value(entrySet.getValue())
                    .build());
        }

        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<PricesErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        var errorResponse = PricesErrorResponse.builder().message("Argument type mismatch").build();
        errorResponse.addError(Error.builder().key(e.getName()).value("Type mismatch").build());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<PricesErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        var errorResponse = PricesErrorResponse.builder().message("Missing query parameter").build();
        errorResponse.addError(Error.builder().key(e.getParameterName()).value("Must be present").build());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<PricesErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        var errorResponse = PricesErrorResponse.builder().message("Missing query parameter").build();
        for (var violation : e.getConstraintViolations()) {
            errorResponse.addError(Error.builder().key(violation.getPropertyPath().toString()).value(violation.getMessage()).build());
        }
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    @Data
    @Builder
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class PricesErrorResponse {
        private String message;
        @Builder.Default
        private List<Error> errors = new ArrayList<>();

        public void addError(Error error) {
            errors.add(error);
        }
    }

    @Data
    @Builder
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class Error {
        private String key;
        private String value;
    }
}