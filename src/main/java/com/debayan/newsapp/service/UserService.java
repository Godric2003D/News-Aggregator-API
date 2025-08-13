package com.debayan.newsapp.service;

import com.debayan.newsapp.entity.User;
import com.debayan.newsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isEmpty()) {
            return "User not found!";
        }

        if (!passwordEncoder.matches(user.getPassword(), String.valueOf(existingUser.getClass()))) {
            return "Invalid password!";
        }

        return "Login successful!";
    }
}
