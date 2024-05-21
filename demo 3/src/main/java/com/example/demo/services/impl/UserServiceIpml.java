package com.example.demo.services.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserServiceIpml implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        Optional<User> existingUser = userRepository.findByLogin(user.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with this login already exists");
        }
        return userRepository.save(user);
    }
    @Override
    public Optional<User> findByLogin(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Login Not Found");
        }
        return userRepository.findByLogin(login);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }


}
