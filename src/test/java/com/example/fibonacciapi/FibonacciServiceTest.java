package com.example.fibonacciapi;

import com.example.fibonacciapi.exceptions.LargeInputException;
import com.example.fibonacciapi.exceptions.NegativeInputException;
import com.example.fibonacciapi.service.FibonacciService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class FibonacciServiceTest {

    @InjectMocks
    private FibonacciService fibonacciService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:[0]", "10:[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]", "20:[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181]"}, delimiter = ':')
    public void testFirstNFibonacci(String input, String expected) {
        long[] result = fibonacciService.firstNFibonacci(Integer.parseInt(input));
        String resultStr = Arrays.toString(result);
        Assertions.assertEquals(expected, resultStr);
    }

    @Test
    public void testFirstNFibonacciInvalidInput() {
        Assertions.assertThrows(LargeInputException.class, () -> {
            fibonacciService.firstNFibonacci(94);
        });
        Assertions.assertThrows(NegativeInputException.class, () -> {
            fibonacciService.firstNFibonacci(0);
        });
        Assertions.assertThrows(NegativeInputException.class, () -> {
            fibonacciService.firstNFibonacci(-10);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"1:0", "2:1", "5:3", "10:34", "20:4181"}, delimiter = ':')
    public void testNthFibonacci(String input, String expected) {
        long result = fibonacciService.nthFibonacci(Integer.parseInt(input));
        String resultStr = Long.toString(result);
        Assertions.assertEquals(expected, resultStr);
    }

    @Test
    public void testNthFibonacciInvalidInput() {
        Assertions.assertThrows(LargeInputException.class, () -> {
            fibonacciService.nthFibonacci(94);
        });
        Assertions.assertThrows(NegativeInputException.class, () -> {
            fibonacciService.nthFibonacci(0);
        });
        Assertions.assertThrows(NegativeInputException.class, () -> {
            fibonacciService.nthFibonacci(-10);
        });
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 8, 13})
    public void testIsFibonacciTrue(long n) {
        Assertions.assertTrue(fibonacciService.isFibonacci(n));
    }

    @ParameterizedTest
    @ValueSource(longs = {10, 122, 134, 565})
    public void testIsFibonacciFalse(long n) {
        Assertions.assertFalse(fibonacciService.isFibonacci(n));
    }

    @Test
    public void testIsFibonacciInvalidInput() {
        Assertions.assertThrows(NegativeInputException.class, () -> {
            fibonacciService.isFibonacci(-120);
        });

        Assertions.assertThrows(LargeInputException.class, () -> {
            fibonacciService.isFibonacci(7540113804746346429L + 1);
        });
    }
}

