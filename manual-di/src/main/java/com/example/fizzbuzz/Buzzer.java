package com.example.fizzbuzz;

public class Buzzer {
    public boolean isBuzz(int i) {
        return i % 5 == 0;
    }

    public String buzz() {
        return "buzz";
    }
}
