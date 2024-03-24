package com.example.fizzbuzz;

import org.springframework.stereotype.Component;

@Component
public class Fizzer {
    public boolean isFizz(int i) {
        return i % 3 == 0;
    }

    public String fizz() {
        return "fizz";
    }
}
