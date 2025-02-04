package com.landingis.api.dto.request.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CourseCreateRequest {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    String courseName;

    @NotNull(message = "Code cannot be null")
    @NotEmpty(message = "Code cannot be empty")
    @Size(min = 3, message = "Code must be at least 8 characters")
    String courseCode;
}
