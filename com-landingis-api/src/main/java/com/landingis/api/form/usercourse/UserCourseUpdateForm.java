package com.landingis.api.form.usercourse;

import com.landingis.api.enumeration.LearningState;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.validation.ValidEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseUpdateForm {

    @ApiModelProperty(value = "User id", example = "1", required = true)
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @ApiModelProperty(value = "Course id", example = "1", required = true)
    @NotNull(message = "Course id cannot be null")
    private Long courseId;

    @ApiModelProperty(value = "Date register", example = "2025-02-08", required = true)
    @Past(message = "Date register must be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateRegister;

    @ApiModelProperty(value = "Register status", example = "PENDING", required = true)
    @NotNull(message = "Register status cannot be null")
    @ValidEnum(enumClass = RegisterStatus.class, message = "Invalid register status")
    private String registerStatus;

    @ApiModelProperty(value = "Learning state", example = "COMPLETED", required = true)
    @NotNull(message = "Learning state cannot be null")
    @ValidEnum(enumClass = LearningState.class, message = "Invalid completion status")
    private String learningState;
}
