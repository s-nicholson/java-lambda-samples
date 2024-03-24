package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.fizzbuzz.Buzzer;
import com.example.fizzbuzz.FizzBuzzService;
import com.example.fizzbuzz.Fizzer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FunctionRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger log = LoggerFactory.getLogger(FunctionRequestHandler.class);

    private final FizzBuzzService fizzBuzzService;
    private final Gson gson;

    public FunctionRequestHandler() {
        Fizzer fizzer = new Fizzer();
        Buzzer buzzer = new Buzzer();

        this.fizzBuzzService = new FizzBuzzService(fizzer, buzzer);
        this.gson = new Gson();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        String endStr = event.getQueryStringParameters().get("end");

        List<String> result = fizzBuzzService.fizzBuzz(Integer.parseInt(endStr));
        String json = gson.toJson(result);

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        response.setBody(json);

        log.info("Manual DI lambda executed");
        return response;
    }
}
