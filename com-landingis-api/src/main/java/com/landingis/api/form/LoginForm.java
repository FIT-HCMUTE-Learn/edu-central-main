package com.landingis.api.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @ApiModelProperty(value = "User name", example = "johndoe", required = true)
    @NotEmpty(message = "User name cannot be empty")
    private String username;

    @ApiModelProperty(value = "Password", example = "Secure@123", required = true)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\W).{6,}$",
            message = "Password must have at least 6 characters, 1 uppercase letter, and 1 special character"
    )
    private String password;
}
