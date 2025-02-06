package com.landingis.api.dto.response.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {
    private Long courseId;
    private String courseName;
    private String courseCode;
}
