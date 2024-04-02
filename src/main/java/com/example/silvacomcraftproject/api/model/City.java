package com.example.silvacomcraftproject.api.model;


public class City {
    private String name;
    private String country;
    private String description;

    // Constructors
    public City() {
    }

    public City(String name, String country, String description) {
        this.name = name;
        this.country = country;
        this.description = description;
    }


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Override toString() method for debugging
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
