package com.CloudTech.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    ResponseEntity<List<Hotel>> getAll() {
        List<Hotel> result = repository.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Hotel>> getById(@PathVariable("id") String id) {
        Optional<Hotel> result = repository.findById(id);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/price/{param0}")
    ResponseEntity<List<Hotel>> getByPrice(@PathVariable("param0") int max) {
        List<Hotel> result = repository.findByPricePerNightLessThan(max);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/city/{param0}")
    ResponseEntity<List<Hotel>> getByCity(@PathVariable("param0") String city) {
        List<Hotel> result = repository.findByCity(city);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    ResponseEntity<Hotel> create(@RequestBody Hotel hotel) {
        Hotel result = repository.insert(hotel);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(uri).body(result);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Hotel> update(@RequestBody Hotel hotel, @PathVariable String id) {
        Hotel result = repository.save(hotel);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PutMapping
    ResponseEntity<Hotel> update(@RequestBody Hotel hotel) {
        Hotel result = repository.save(hotel);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    ResponseEntity<Object> delete(@RequestBody Hotel hotel) {
        repository.delete(hotel);
        return ResponseEntity.noContent().build();
    }
}