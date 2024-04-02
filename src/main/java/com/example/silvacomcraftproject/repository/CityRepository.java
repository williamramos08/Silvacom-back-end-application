package com.example.silvacomcraftproject.repository;

import com.example.silvacomcraftproject.api.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository extends MongoRepository<City, String> {
}
