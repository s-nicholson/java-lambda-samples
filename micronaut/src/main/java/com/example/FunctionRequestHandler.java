package com.example;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.fizzbuzz.FizzBuzzService;
import io.micronaut.function.aws.MicronautRequestHandler;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class FunctionRequestHandler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger log = LoggerFactory.getLogger(FunctionRequestHandler.class);

    @Inject
    private JsonMapper objectMapper;
    @Inject
    private FizzBuzzService fizzBuzzService;

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent event) {
        String endStr = event.getQueryStringParameters().get("end");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try {
            List<String> result = fizzBuzzService.fizzBuzz(Integer.parseInt(endStr));
            String json = new String(objectMapper.writeValueAsBytes(result));
            response.setStatusCode(200);
            response.setBody(json);
        } catch (IOException e) {
            response.setStatusCode(500);
        }
        log.info("Micronaut lambda executed");
        return response;
    }
}
