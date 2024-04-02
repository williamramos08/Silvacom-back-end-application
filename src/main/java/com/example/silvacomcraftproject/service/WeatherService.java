package com.example.silvacomcraftproject.service;

import com.example.silvacomcraftproject.api.model.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Value("${weather.forecast.api.url}")
    private String weatherForecastApiUrl;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getCurrentWeather(String city) throws JsonProcessingException {
        String apiUrl = weatherApiUrl + "?q=" + city + "&key=" + weatherApiKey;
        //remove api url print
        System.out.println(apiUrl);
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);
        JsonNode locationNode = rootNode.path("location");
        JsonNode currentWeatherNode = rootNode.path("current");

        String cityName = locationNode.path("name").asText();
        String weatherDescription = currentWeatherNode.path("condition").path("text").asText();
        double temperature = currentWeatherNode.path("temp_c").asDouble();
        double windSpeed = currentWeatherNode.path("wind_kph").asDouble();

        return new WeatherResponse(cityName, weatherDescription, temperature, windSpeed);
    }

    public List<WeatherResponse> getWeatherForecast(String city, int days) throws JsonProcessingException {
        List<WeatherResponse> weatherForecast = new ArrayList<>();

        // Fetch weather forecast for the specified number of days
        String apiUrl = weatherForecastApiUrl + "?q=" + city + "&key=" + weatherApiKey + "&days=" + days;
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);
        JsonNode forecastNode = rootNode.path("forecast").path("forecastday");

        for (JsonNode dayNode : forecastNode) {
            String cityName = city;
            String weatherDescription = dayNode.path("day").path("condition").path("text").asText();
            double temperature = dayNode.path("day").path("avgtemp_c").asDouble();
            double windSpeed = dayNode.path("day").path("maxwind_kph").asDouble();
            String date = dayNode.path("date").asText();

            WeatherResponse weatherResponse = new WeatherResponse(cityName, weatherDescription, temperature, windSpeed, date);
            weatherForecast.add(weatherResponse);
        }

        return weatherForecast;
    }

}
