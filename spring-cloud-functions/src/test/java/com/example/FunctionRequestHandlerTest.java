package com.example;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;

import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FunctionalSpringBootTest
public class FunctionRequestHandlerTest {
    @Autowired
    private FunctionCatalog functionCatalog;

    @Test
    public void testHandler() {

        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("GET");
        request.setPath("/");
        request.setQueryStringParameters(Map.of("end", "15"));

        var handler = functionCatalog
                .<Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>>lookup(
                        Function.class, "handleRequest");
        APIGatewayProxyResponseEvent response = handler.apply(request);

        assertEquals(200, response.getStatusCode().intValue());
        assertEquals(
                "[\"1\",\"2\",\"fizz\",\"4\",\"buzz\",\"fizz\",\"7\",\"8\",\"fizz\",\"buzz\",\"11\",\"fizz\",\"13\",\"14\",\"fizzbuzz\"]",
                response.getBody());
    }
}
