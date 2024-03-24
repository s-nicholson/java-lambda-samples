package com.example.fizzbuzz;

import javax.inject.Inject;
import javax.inject.Singleton;

public class Fizzer {
    @Inject
    public Fizzer() {}

    public boolean isFizz(int i) {
        return i % 3 == 0;
    }

    public String fizz() {
        return "fizz";
    }
}
