package com.project.financetracker.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberParsingError(NumberFormatException exception) {
        // System.out.println("Handling an exception...");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR: You entered an invalid integer/decimal.");
    }

}
