package com.example;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.fizzbuzz.Buzzer;
import com.example.fizzbuzz.FizzBuzzService;
import com.example.fizzbuzz.Fizzer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionRequestHandlerTest {
    private static FunctionRequestHandler handler;

    @BeforeAll
    static void setup() {
        handler = new FunctionRequestHandler();
    }

    @Test
    public void testHandler() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("GET");
        request.setPath("/");
        request.setQueryStringParameters(Map.of("end", "15"));
        APIGatewayProxyResponseEvent response = handler.handleRequest(request, null);
        assertEquals(200, response.getStatusCode().intValue());
        assertEquals(
                "[\"1\",\"2\",\"fizz\",\"4\",\"buzz\",\"fizz\",\"7\",\"8\",\"fizz\",\"buzz\",\"11\",\"fizz\",\"13\",\"14\",\"fizzbuzz\"]",
                response.getBody());
    }
}
