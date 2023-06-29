package com.example.fibonacciapi.exceptions;

public class NegativeInputException extends RuntimeException {

    public NegativeInputException() {
        super("Your input number is negative, please provide a number > 0");
    }

    public NegativeInputException(String errorMsg) {
        super(errorMsg);
    }

}
