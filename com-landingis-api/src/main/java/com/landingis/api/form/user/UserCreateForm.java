package com.landingis.api.form.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateForm {

    @ApiModelProperty(value = "User handle (username)", example = "johndoe", required = true)
    @NotEmpty(message = "Handle cannot be empty")
    private String handle;

    @ApiModelProperty(value = "User password", example = "securePass123", required = true)
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String userPassword;

    @ApiModelProperty(value = "Full name", example = "John Doe", required = true)
    @NotEmpty(message = "Full name cannot be empty")
    private String userFullName;

    @ApiModelProperty(value = "User birth date (must be in the past)", example = "2000-01-15", required = true)
    @Past(message = "Birth date must be in the past")
    private Date userBirthday;
}
