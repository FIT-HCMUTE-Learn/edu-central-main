package com.landingis.api.service.impl;

import com.landingis.api.dto.AuthenticationDto;
import com.landingis.api.exception.AuthenticationException;
import com.landingis.api.form.LoginForm;
import com.landingis.api.model.entity.Admin;
import com.landingis.api.model.entity.User;
import com.landingis.api.repository.AdminRepository;
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
    private AdminRepository adminRepository;

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

        // Check Super admin
        boolean isSuperAdmin = false;
        if ("GROUP_ADMIN".equals(user.getGroup().getName())) {
            Optional<Admin> adminOptional = adminRepository.findByUserId(user.getId());
            isSuperAdmin = adminOptional.map(Admin::getIsSuperAdmin).orElse(false);
        }

        // Create CustomUserDetails from User
        CustomUserDetails userDetails = new CustomUserDetails(user, isSuperAdmin);

        // Authenticate user using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(),
                        loginForm.getPassword()
                )
        );

        // Save authentication to SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Convert GrantedAuthority to String list
        List<String> pcodes = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Generate JWT Token
        String token = jwtUtil.generateToken(userDetails, pcodes);

        return new AuthenticationDto(token, userDetails.getUsername(), pcodes);
    }

    @Override
    public boolean checkIsSuperAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getIsSuperAdmin();
        }

        return false;
    }
}
