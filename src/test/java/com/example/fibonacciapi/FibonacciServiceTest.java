package com.example.fibonacciapi;

import com.example.fibonacciapi.exceptions.LargeInputException;
import com.example.fibonacciapi.exceptions.NegativeInputException;
import com.example.fibonacciapi.service.FibonacciService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class FibonacciServiceTest {

    @InjectMocks
    private FibonacciService fibonacciService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFirstNFibonacci() {
        long[] expected_1 = {0};
        long[] result_1 = fibonacciService.firstNFibonacci(1);

        long[] expected_10 = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
        long[] result_10 = fibonacciService.firstNFibonacci(10);

        long[] expected_20 = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181};
        long[] result_20 = fibonacciService.firstNFibonacci(20);

        Assertions.assertArrayEquals(expected_1, result_1);
        Assertions.assertArrayEquals(expected_10, result_10);
        Assertions.assertArrayEquals(expected_20, result_20);
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

    @Test
    public void testNthFibonacci() {
        long expected_1 = 0;
        long result_1 = fibonacciService.nthFibonacci(1);

        long expected_5 = 3;
        long result_5 = fibonacciService.nthFibonacci(5);

        long expected_10 = 34;
        long result_10 = fibonacciService.nthFibonacci(10);

        Assertions.assertEquals(expected_1, result_1);
        Assertions.assertEquals(expected_5, result_5);
        Assertions.assertEquals(expected_10, result_10);
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

    @Test
    public void testIsFibonacci() {
        Assertions.assertTrue(fibonacciService.isFibonacci(0));
        Assertions.assertTrue(fibonacciService.isFibonacci(1));
        Assertions.assertTrue(fibonacciService.isFibonacci(8));
        Assertions.assertTrue(fibonacciService.isFibonacci(13));
        Assertions.assertFalse(fibonacciService.isFibonacci(4));
        Assertions.assertFalse(fibonacciService.isFibonacci(10));
        Assertions.assertFalse(fibonacciService.isFibonacci(122));
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

