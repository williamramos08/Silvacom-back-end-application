package com.example.silvacomcraftproject.service;

import com.example.silvacomcraftproject.api.model.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private List<City> cities;

    public CityService() {
        // Initialize the list of cities with the provided JSON data
        cities = new ArrayList<>();
        cities.add(new City("New York", "United States", "New York City is one of the most populous cities in the United States. It is known for its iconic landmarks such as the Statue of Liberty, Times Square, and Central Park."));
        cities.add(new City("London", "United Kingdom", "London is the capital city of the United Kingdom and is famous for its rich history, cultural diversity, and iconic landmarks like the Big Ben, Buckingham Palace, and the London Eye."));
        cities.add(new City("Tokyo", "Japan", "Tokyo, the capital of Japan, is a bustling metropolis known for its vibrant street life, cutting-edge technology, and historic temples and shrines."));
        cities.add(new City("Paris", "France", "Paris, the capital city of France, is renowned for its art, fashion, cuisine, and iconic landmarks such as the Eiffel Tower, Louvre Museum, and Notre-Dame Cathedral."));
        cities.add(new City("Sydney", "Australia", "Sydney is the largest city in Australia and is famous for its stunning beaches, iconic Sydney Opera House, and vibrant cultural scene."));
    }

    public City getCity(String name) {
        // Find the city by name in the list
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null; // Return null if city is not found
    }

    public List<City> getAllCities() {
        return cities; // Return the list of all cities
    }

    public City saveCity(City city) {
        // Since we are hardcoding data, saving a city would not have any effect
        // You may choose to throw an exception or return a default city
        return null;
    }

    public void deleteCity(String name) {
        // Since we are hardcoding data, deleting a city would not have any effect
        // You may choose to throw an exception or handle it in a different way
    }
}
