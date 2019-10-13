package com.newsservice.newsservice.controller;

import com.newsservice.newsservice.model.Role;
import com.newsservice.newsservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository repository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/role")
    Role role(@RequestBody Role role) {
        return repository.save(role);
    }

    @DeleteMapping("/role/{id}")
    void deleteNews(@PathVariable Long id){
        repository.deleteById(id);
    }
}
