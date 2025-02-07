package com.landingis.api.form.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateForm {

    @ApiModelProperty(value = "Course name", example = "Web Development", required = true)
    @NotEmpty(message = "Course name cannot be empty")
    private String courseName;

    @ApiModelProperty(value = "Course code", example = "CS101", required = true)
    @NotEmpty(message = "Course code cannot be empty")
    @Size(min = 4, message = "Course code must be at least 4 characters")
    private String courseCode;
}
