package com.debayan.newsapp.controller;

import com.debayan.newsapp.dto.*;
import com.debayan.newsapp.entity.User;
import com.debayan.newsapp.repository.UserRepository;
import com.debayan.newsapp.service.CustomUserDetailsService;
import com.debayan.newsapp.service.NewsService;
import com.debayan.newsapp.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private NewsService newsService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest req) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(Map.of("token", jwt));
    }

    @GetMapping("/preferences")
    public ResponseEntity<List<String>> getPreferences(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).get();
        return ResponseEntity.ok(user.getPreferences());
    }

    @PutMapping("/preferences")
    public ResponseEntity<String> updatePreferences(Authentication auth, @RequestBody List<String> prefs) {
        User user = userRepository.findByUsername(auth.getName()).get();
        user.setPreferences(prefs);
        userRepository.save(user);
        return ResponseEntity.ok("Preferences updated");
    }

    @GetMapping("/news")
    public ResponseEntity<List<String>> getNews(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).get();
        List<String> news = newsService.fetchNews(user.getPreferences());
        return ResponseEntity.ok(news);
    }
}
