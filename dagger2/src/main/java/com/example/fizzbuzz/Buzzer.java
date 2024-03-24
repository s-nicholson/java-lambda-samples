package com.example.fizzbuzz;

import javax.inject.Inject;
import javax.inject.Singleton;

public class Buzzer {
    @Inject
    public Buzzer() {}

    public boolean isBuzz(int i) {
        return i % 5 == 0;
    }

    public String buzz() {
        return "buzz";
    }
}
