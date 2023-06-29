package com.example.fibonacciapi.web;

import com.example.fibonacciapi.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FibonacciController {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @GetMapping("/fibonacciSeries/first/{n}")
    public ResponseEntity<?> getFirstNFibonacciNumbers(@PathVariable int n) {
        try {
            long[] result = fibonacciService.firstNFibonacci(n);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fibonacciSeries/{n}")
    public ResponseEntity<?> getNthFibonacci(@PathVariable int n) {
        try {
            long result = fibonacciService.nthFibonacci(n);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fibonacciSeries/validate/{n}")
    public ResponseEntity<String> isFibonacciNumber(@PathVariable long n) {
        try {
            if (fibonacciService.isFibonacci(n)) {
                return new ResponseEntity<>(n + " is a Fibonacci number, nice :)", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(n + " is not a Fibonacci number, what a shame :(", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
