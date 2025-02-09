package com.landingis.api.form.usercourse;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseRegisterForm {

    @ApiModelProperty(value = "User id", example = "1", required = true)
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @ApiModelProperty(value = "Course id", example = "1", required = true)
    @NotNull(message = "Course id cannot be null")
    private Long courseId;
}
