package com.landingis.api.service;

import com.landingis.api.dto.AuthenticationDto;
import com.landingis.api.form.LoginForm;

public interface AuthenticationService {
    AuthenticationDto authenticateUser(LoginForm loginForm);
}
