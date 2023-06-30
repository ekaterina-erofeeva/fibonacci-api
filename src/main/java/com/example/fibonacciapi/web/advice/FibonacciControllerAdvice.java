package com.example.fibonacciapi.web.advice;

import com.example.fibonacciapi.exceptions.LargeInputException;
import com.example.fibonacciapi.exceptions.NegativeInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FibonacciControllerAdvice {

    @ExceptionHandler(LargeInputException.class)
    public ResponseEntity<String> handleNumberFormatException(LargeInputException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NegativeInputException.class)
    public ResponseEntity<String> handleNegativeNumberException(NegativeInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
