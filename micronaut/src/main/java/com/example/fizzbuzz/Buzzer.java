package com.example.fizzbuzz;

import jakarta.inject.Singleton;

@Singleton
public class Buzzer {
    public boolean isBuzz(int i) {
        return i % 5 == 0;
    }

    public String buzz() {
        return "buzz";
    }
}
