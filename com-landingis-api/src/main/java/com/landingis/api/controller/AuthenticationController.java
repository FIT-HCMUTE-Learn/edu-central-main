package com.landingis.api.controller;

import com.landingis.api.dto.AuthenticationDto;
import com.landingis.api.exception.AuthenticationException;
import com.landingis.api.form.LoginForm;
import com.landingis.api.model.User;
import com.landingis.api.model.Permission;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {
        Optional<User> userOptional = userRepository.findByUsername(loginForm.getUsername());

//        if (userOptional.isEmpty() || !passwordEncoder.matches(loginForm.getPassword(), userOptional.get().getPassword())) {
//            throw new AuthenticationException("Invalid username or password");
//        }

        if (userOptional.isEmpty()) {
            throw new AuthenticationException("Invalid username or password");
        }

        User user = userOptional.get();
        List<String> pcodes = user.getPermissions().stream()
                .map(Permission::getPcode)
                .collect(Collectors.toList());

        String token = jwtUtil.generateToken(user.getUsername(), pcodes);

        return ResponseEntity.ok(new AuthenticationDto(token, user.getUsername(), pcodes));
    }
}
