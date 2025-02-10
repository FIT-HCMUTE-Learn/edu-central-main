package com.landingis.api.form.user;

import com.landingis.api.validation.GenderConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateForm {

    @ApiModelProperty(value = "User id", example = "1", required = true)
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @ApiModelProperty(value = "User handle (username)", example = "johndoe", required = true)
    @NotEmpty(message = "Handle cannot be empty")
    private String handle;

    @ApiModelProperty(value = "User password", example = "Secure@123", required = true)
    @NotEmpty(message = "Password cannot be empty")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\W).{6,}$",
            message = "Password must have at least 6 characters, 1 uppercase letter, and 1 special character"
    )
    private String userPassword;

    @ApiModelProperty(value = "Full name", example = "John Doe", required = true)
    @NotEmpty(message = "Full name cannot be empty")
    private String userFullName;

    @ApiModelProperty(value = "User birth date (must be in the past)", example = "2000-01-15", required = true)
    @Past(message = "Birth date must be in the past")
    private Date userBirthday;

    @ApiModelProperty(value = "Gender (1: Male, 2: Female, 3: Other)", example = "1", required = false)
    @GenderConstraint(allowNull = true)
    private Integer userGender;
}
