package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    @PostMapping(value = "/register")
    public User register(@RequestBody User user){
        // Хэширование пароля перед сохранением пользователя
        return userService.save(user);
    }

    @GetMapping(value = "/{login}")
    public Optional<User> getUserByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @GetMapping("/all_users")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }
}
