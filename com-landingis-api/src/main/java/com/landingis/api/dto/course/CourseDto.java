package com.landingis.api.dto.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto {
    private Long courseId;
    private String courseName;
    private String courseCode;
}
