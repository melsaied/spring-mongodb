package com.CloudTech.springmongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    HotelRepository repository;

    public Seeder(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Hotel> list = Arrays.asList(
                new Hotel(
                        "hotel1",
                        200,
                        new Address(
                                "city1",
                                "country1"),
                        Arrays.asList(new Review(
                                        "user1",
                                        5,
                                        true),
                                new Review(
                                        "user2",
                                        4,
                                        true),
                                new Review(
                                        "user3",
                                        3,
                                        false))),
                new Hotel(
                        "hotel2",
                        300,
                        new Address(
                                "city2",
                                "country2"),
                        Arrays.asList(new Review(
                                        "user2",
                                        5,
                                        true),
                                new Review(
                                        "user1",
                                        1,
                                        false))),
                new Hotel(
                        "hotel3",
                        100,
                        new Address(
                                "city3",
                                "country3"),
                        null));
        this.repository.deleteAll();
        this.repository.saveAll(list);
    }
}
