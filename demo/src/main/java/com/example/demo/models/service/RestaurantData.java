package com.example.demo.models.service;

import com.example.demo.models.ResuarantModels.Restaurant;
import com.example.demo.models.jsonParsing.Json;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
@Service
public class RestaurantData {
    @Autowired
    RestTemplate restTemplate;
    public static ArrayList<Restaurant> returnRestaurantData() throws IOException, InterruptedException {

            final String POSTS_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/" +
                    "json?location=38.627003%2C-90.199402&radius=2000&type=restaurant&fields=name%2C" +
                    "place_id&key=AIzaSyDOM2GJ-EcQaX8dteJtDc8q1bpf-5tut10";
            /*final String POSTS_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/" +
                    "json?location=38.627003%2C-90.199402&radius=500&type=restaurant&fields=name%2C" +
                    "place_id&key=YOUR_API_KEY";*/

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(POSTS_API_URL))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

           JsonNode node = Json.parse(response.body());
            JsonNode results = node.get("results");
           // Restaurant restaurant = Json.fromJson(results.get(0), Restaurant.class);

            //return restaurant;
            ArrayList<Restaurant> restaurants = new ArrayList<>();

            for (JsonNode result : results) {
                Restaurant restaurant = Json.fromJson(result, Restaurant.class);
                restaurants.add(restaurant);
            }

            return restaurants;
        }
    }
