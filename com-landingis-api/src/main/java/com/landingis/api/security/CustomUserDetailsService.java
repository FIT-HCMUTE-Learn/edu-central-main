package com.landingis.api.security;

import com.landingis.api.model.Admin;
import com.landingis.api.model.User;
import com.landingis.api.repository.AdminRepository;
import com.landingis.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean isSuperAdmin = false;
        if ("GROUP_ADMIN".equals(user.getGroup().getName())) {
            Optional<Admin> adminOptional = adminRepository.findByUserId(user.getId());
            isSuperAdmin = adminOptional.map(Admin::getIsSuperAdmin).orElse(false);
        }

        return new CustomUserDetails(user, isSuperAdmin);
    }
}
