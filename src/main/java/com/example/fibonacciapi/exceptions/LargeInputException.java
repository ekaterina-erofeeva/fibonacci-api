package com.example.fibonacciapi.exceptions;

public class LargeInputException extends RuntimeException {

    public LargeInputException(String errorMsg) {
        super(errorMsg);
    }
}
