package com.landingis.api.controller;

import com.landingis.api.dto.AuthDto;
import com.landingis.api.form.LoginForm;
import com.landingis.api.model.User;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        Optional<User> user = userRepository.findByUsername(loginForm.getUsername());

//        if (user.isEmpty() || !passwordEncoder.matches(loginForm.getPassword(), user.get().getPassword())) {
//            throw new RuntimeException("Invalid username or password");
//        }

        String token = jwtUtil.generateToken(user.get().getUsername(), user.get().getRole().name());
        return ResponseEntity.ok(new AuthDto(token, user.get().getUsername(), user.get().getRole().name()));
    }
}
