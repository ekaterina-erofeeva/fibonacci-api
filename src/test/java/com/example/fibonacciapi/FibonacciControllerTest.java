package com.example.fibonacciapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FibonacciControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFirstNFibonacci() throws Exception {
        mockMvc.perform(get("/fibonacciSeries/first/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[0]"));

        mockMvc.perform(get("/fibonacciSeries/first/5"))
                .andExpect(status().isOk())
                .andExpect(content().json("[0, 1, 1, 2, 3]"));

        mockMvc.perform(get("/fibonacciSeries/first/10"))
                .andExpect(status().isOk())
                .andExpect(content().json("[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"));

    }

    @Test
    public void testGetFirstNFibonacciInvalidInput() throws Exception {
        mockMvc.perform(get("/fibonacciSeries/first/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Your input number is negative, please provide a number > 0"));

        mockMvc.perform(get("/fibonacciSeries/first/-10"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Your input number is negative, please provide a number > 0"));
    }

    @Test
    public void testGetNthFibonacci() throws Exception {
        mockMvc.perform(get("/fibonacciSeries/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));

        mockMvc.perform(get("/fibonacciSeries/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));

        mockMvc.perform(get("/fibonacciSeries/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("34"));
    }

    @Test
    public void testGetNthFibonacciInvalidInput() throws Exception {
        mockMvc.perform(get("/fibonacciSeries/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Your input number is negative, please provide a number > 0"));

        mockMvc.perform(get("/fibonacciSeries/94"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Your input number is too large, choose a number <= 93"));
    }


    @Test
    public void testGetIsFibonacciFibonacci() throws Exception {
        mockMvc.perform(get("/fibonacciSeries/validate/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0 is a Fibonacci number, nice :)"));

        mockMvc.perform(get("/fibonacciSeries/validate/8"))
                .andExpect(status().isOk())
                .andExpect(content().string("8 is a Fibonacci number, nice :)"));

        mockMvc.perform(get("/fibonacciSeries/validate/122"))
                .andExpect(status().isOk())
                .andExpect(content().string("122 is not a Fibonacci number, what a shame :("));
    }

    @Test
    public void tesGetIsFibonacciInvalidInput() throws Exception {
        mockMvc.perform(get("/fibonacciSeries/validate/-10"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Your input number is negative, please provide a number >= 0"));

        mockMvc.perform(get("/fibonacciSeries/validate/7540113804746346430"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This input number is too large to be checked"));
    }
}

