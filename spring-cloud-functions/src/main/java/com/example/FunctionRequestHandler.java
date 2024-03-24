package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.fizzbuzz.Buzzer;
import com.example.fizzbuzz.FizzBuzzService;
import com.example.fizzbuzz.Fizzer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class FunctionRequestHandler {
    private static final Logger log = LoggerFactory.getLogger(FunctionRequestHandler.class);

    @Bean
    public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> handleRequest(
            @Autowired FizzBuzzService fizzBuzzService,
            @Autowired ObjectMapper objectMapper
    ) {
        return event -> {
            String endStr = event.getQueryStringParameters().get("end");
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            try {
                List<String> result = fizzBuzzService.fizzBuzz(Integer.parseInt(endStr));
                String json = objectMapper.writeValueAsString(result);
                response.setStatusCode(200);
                response.setBody(json);
            } catch (Exception e) {
                response.setStatusCode(500);
            }
            log.info("SCF lambda executed");
            return response;
        };
    }

    public static void main(String[] args) {
    }
}
