package com.example.demo.models.jsonParsing;

import com.example.demo.models.ResuarantModels.Restaurant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // ---configurations---
      //  defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }
    //added on july 17
    public static JsonNode parse(String str) throws IOException {
        return objectMapper.readTree(str);
    }


    public static Restaurant fromJson(JsonNode result, Class<Restaurant> restaurantClass) throws JsonProcessingException {
        return objectMapper.treeToValue(result,restaurantClass);
    }


}