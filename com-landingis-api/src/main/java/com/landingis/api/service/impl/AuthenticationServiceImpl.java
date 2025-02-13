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
        // Check if the username exists
        Optional<User> userOptional = userRepository.findByUsername(loginForm.getUsername());
        if (userOptional.isEmpty()) {
            throw new AuthenticationException("Invalid username");
        }

        User user = userOptional.get();

        // Check if the password matches
        if (!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        // Authenticate user using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(),
                        loginForm.getPassword()
                )
        );

        // Set authentication in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Extract group name & kind
        String groupName = user.getGroup().getName();
        Integer groupKind = user.getGroup().getKind();

        // Convert GrantedAuthority to List<String>
        List<String> pcodes = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Extract permission code
                .collect(Collectors.toList());

        // Generate JWT token with group info
        String token = jwtUtil.generateToken(userDetails.getUserId(), userDetails.getUsername(), groupName, groupKind, pcodes);

        return new AuthenticationDto(token, userDetails.getUsername(), pcodes);
    }
}
