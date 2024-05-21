package com.example.demo.services;

import com.example.demo.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByLogin(String login);
    Iterable<User> findAll();
}
