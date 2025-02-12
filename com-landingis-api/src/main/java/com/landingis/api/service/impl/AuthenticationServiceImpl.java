package com.landingis.api.service.impl;

import com.landingis.api.dto.AuthenticationDto;
import com.landingis.api.exception.AuthenticationException;
import com.landingis.api.form.LoginForm;
import com.landingis.api.model.User;
import com.landingis.api.security.CustomUserDetails;
import com.landingis.api.service.AuthenticationService;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationDto authenticateUser(LoginForm loginForm) {
        // Check username exists
        Optional<User> userOptional = userRepository.findByUsername(loginForm.getUsername());
        if (userOptional.isEmpty()) {
            throw new AuthenticationException("Invalid username");
        }

        User user = userOptional.get();

        // Check password matches
        if (!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        // Use AuthenticationManager to verify username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(),
                        loginForm.getPassword()
                )
        );

        // Set authentication in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Convert GrantedAuthority to List<String>
        List<String> pcodes = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Extract permission code
                .collect(Collectors.toList());

        // Generate JWT token
        String token = jwtUtil.generateToken(userDetails.getUsername(), pcodes);

        return new AuthenticationDto(token, userDetails.getUsername(), pcodes);
    }
}
