package com.example.fizzbuzz;

import org.springframework.stereotype.Component;

@Component
public class Buzzer {
    public boolean isBuzz(int i) {
        return i % 5 == 0;
    }

    public String buzz() {
        return "buzz";
    }
}
