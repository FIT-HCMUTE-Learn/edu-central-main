package com.landingis.api.controller;

import com.landingis.api.dto.JwtDto;
import com.landingis.api.form.LoginForm;
import com.landingis.api.model.User;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        // Find user by username
        User user = userRepository.findByUsername(loginForm.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        // Verify password using BCrypt
//        if (!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
//        }

        // Generate JWT Token containing role
        String token = jwtUtils.generateToken(user);

        // Return token, username, and role
        return ResponseEntity.ok(new JwtDto(token, user.getUsername(), user.getRole().name()));
    }
}
