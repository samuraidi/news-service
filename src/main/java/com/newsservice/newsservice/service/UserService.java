package com.newsservice.newsservice.service;

import com.newsservice.newsservice.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> findAllUsers();
}
