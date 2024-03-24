package com.example;

import com.example.fizzbuzz.FizzBuzzService;
import com.google.gson.Gson;
import dagger.Component;

@Component(modules = {
        LambdaModule.class
})
public interface Factory {
    FizzBuzzService service();
    Gson gson();
}
