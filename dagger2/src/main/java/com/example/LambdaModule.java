package com.example;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;

@Module
public class LambdaModule {
    @Provides
    Gson gson() {
        return new Gson();
    }
}
