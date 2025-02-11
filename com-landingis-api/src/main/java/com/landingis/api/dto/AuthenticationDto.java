package com.landingis.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthenticationDto {
    private String token;
    private String username;
    private List<String> permissions;
}
