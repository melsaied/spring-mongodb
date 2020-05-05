package com.CloudTech.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<User>> getAll() {
        List<User> result = repository.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<User>> getById(@PathVariable("id") String id) {
        Optional<User> result = repository.findById(id);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/filter0/{param0}")
    ResponseEntity<List<User>> getByPrice(@PathVariable("param0") String param0) {
        List<User> result = repository.findByEmail(param0);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    ResponseEntity<User> create(@RequestBody User hotel) {
        User result = repository.insert(hotel);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(uri).body(result);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<User> update(@RequestBody User hotel, @PathVariable String id) {
        User result = repository.save(hotel);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PutMapping
    ResponseEntity<User> update(@RequestBody User hotel) {
        User result = repository.save(hotel);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    ResponseEntity<Object> delete(@RequestBody User object) {
        repository.delete(object);
        return ResponseEntity.ok().build();
    }
}