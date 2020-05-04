package com.CloudTech.springmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review,String> {
}
