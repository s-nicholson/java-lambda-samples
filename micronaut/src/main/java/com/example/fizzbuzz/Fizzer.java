package com.example.fizzbuzz;

import jakarta.inject.Singleton;

@Singleton
public class Fizzer {
    public boolean isFizz(int i) {
        return i % 3 == 0;
    }

    public String fizz() {
        return "fizz";
    }
}
