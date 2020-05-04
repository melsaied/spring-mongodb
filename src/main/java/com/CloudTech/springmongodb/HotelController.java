package com.CloudTech.springmongodb;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    List<Hotel> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Hotel> getById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping("/price/{max}")
    List<Hotel> getById(@PathVariable("max") int max) {
        return repository.findByPricePerNightLessThan(max);
    }

    @PostMapping
    Hotel add(@RequestBody Hotel hotel) {
        return repository.insert(hotel);
    }

    @PutMapping
    Hotel update(@RequestBody Hotel hotel) {
        return repository.save(hotel);
    }

    @DeleteMapping
    void delete(@RequestBody Hotel hotel) {
        repository.delete(hotel);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
