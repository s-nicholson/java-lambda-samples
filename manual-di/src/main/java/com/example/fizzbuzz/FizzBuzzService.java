package com.example.fizzbuzz;

import java.util.List;
import java.util.stream.IntStream;

public class FizzBuzzService {
    private final Fizzer fizzer;
    private final Buzzer buzzer;

    public FizzBuzzService(Fizzer fizzer, Buzzer buzzer) {
        this.fizzer = fizzer;
        this.buzzer = buzzer;
    }

    public List<String> fizzBuzz(int end) {
        return IntStream.rangeClosed(1, end)
                .mapToObj(this::toFizzBuzz)
                .toList();
    }

    private String toFizzBuzz(int i) {
        StringBuilder sb = new StringBuilder();
        if (fizzer.isFizz(i)) {
            sb.append(fizzer.fizz());
        }
        if (buzzer.isBuzz(i)) {
            sb.append(buzzer.buzz());
        }
        if (sb.isEmpty()) {
            sb.append(i);
        }
        return sb.toString();
    }
}
