package com.example.fibonacciapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @CsvSource(value = {"1:[0]", "10:[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]", "20:[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181]"}, delimiter = ':')
    public void testGetFirstNFibonacci(String input, String expected) throws Exception {
        mockMvc.perform(get("/fibonacciSeries/first/" + input))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10, -120})
    public void testGetFirstNFibonacciInvalidInput(int n) throws Exception {
        mockMvc.perform(get("/fibonacciSeries/first/" + n))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Your input number is negative, please provide a number > 0"));
    }

    @ParameterizedTest
    @CsvSource(value = {"1:0", "5:3", "10:34", "20:4181"}, delimiter = ':')
    public void testGetNthFibonacci(String input, String expected) throws Exception {
        mockMvc.perform(get("/fibonacciSeries/" + input))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
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

