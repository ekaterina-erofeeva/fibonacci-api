package com.example.fibonacciapi.service;

import com.example.fibonacciapi.exceptions.LargeInputException;
import com.example.fibonacciapi.exceptions.NegativeInputException;
import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

    public long nthFibonacci(int n) {
        if (n <= 0) {
            throw new NegativeInputException();
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            long a = 0;
            long b = 1;
            long c = 0;
            for (int i = 2; i < n; i++) {
                c = a;
                a = b;
                b = c + b;
                if (b < 0) {
                    throw new LargeInputException("Your input number is too large, choose a number <= " + (n - 1));
                }
            }
            return b;
        }
    }

    public long[] firstNFibonacci(int n) {
        if (n <= 0) {
            throw new NegativeInputException();
        }
        long[] result = new long[n];
        result[0] = 0;
        if (n >= 2) {
            result[1] = 1;
            for (int i = 2; i < n; i++) {
                result[i] = result[i - 1] + result[i - 2];
                if (result[i] < 0) {
                    throw new LargeInputException("Your input number is too large, choose a number <= " + (n - 1));
                }
            }
        }
        return result;
    }

    public boolean isFibonacci(long n) {
        if (n < 0) {
            throw new NegativeInputException("Your input number is negative, please provide a number >= 0");
        } else if (n == 0) {
            return true;
        } else if (n == 1) {
            return true;
        }
        long a = 0;
        long b = 1;

        while (a + b <= n) {
            if (a + b < 0) {
                throw new LargeInputException("This input number is too large to be checked");
            };

            if (a + b == n) {
                return true;
            }
            long c = a;
            a = b;
            b = c + b;
        }
        return false;
    }
}
