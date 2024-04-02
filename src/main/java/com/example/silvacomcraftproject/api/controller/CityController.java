package com.example.silvacomcraftproject.api.controller;

import com.example.silvacomcraftproject.api.model.City;
import com.example.silvacomcraftproject.api.model.WeatherResponse;
import com.example.silvacomcraftproject.service.CityService;
import com.example.silvacomcraftproject.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    private CityService cityService;
    private WeatherService weatherService;
    @Autowired
    public CityController(CityService cityService, WeatherService weatherService){
        this.cityService = cityService;
        this.weatherService = weatherService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/city")
    public City getCity(@RequestParam String id){
        return cityService.getCity(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/city/weather")
    public WeatherResponse getCityWeather(@RequestParam String id) throws JsonProcessingException {
        return weatherService.getCurrentWeather(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/city/forecast")
    public List<WeatherResponse> getCityForecast(@RequestParam String id, @RequestParam(required = false) Integer days) throws JsonProcessingException {
        if (days == null) {
            // Default to today's weather if no days parameter is provided
            return List.of(weatherService.getCurrentWeather(id));
        }
        return weatherService.getWeatherForecast(id, days);
    }

}
