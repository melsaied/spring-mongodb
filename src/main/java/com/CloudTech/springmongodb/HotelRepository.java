package com.CloudTech.springmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, String> {
    List<Hotel> findByPricePerNightLessThan(int maxPrice);
}
