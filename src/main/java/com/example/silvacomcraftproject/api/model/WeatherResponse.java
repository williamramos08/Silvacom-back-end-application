package com.example.silvacomcraftproject.api.model;

public class WeatherResponse {
    private String cityName;
    private String weatherDescription;
    private double temperature;
    private double windSpeed;
    private String date;

    // Constructors, getters, and setters
    public WeatherResponse() {
    }

    public WeatherResponse(String cityName, String weatherDescription, double temperature, double windSpeed) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }

    public WeatherResponse(String cityName, String weatherDescription, double temperature, double windSpeed, String date) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
