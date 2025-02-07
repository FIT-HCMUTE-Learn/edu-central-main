package com.landingis.api.dto.response.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDtoResponse {
    private Long courseId;
    private String courseName;
    private String courseCode;
}
