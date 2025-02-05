package com.landingis.api.dto.response.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CourseResponse {
    Long courseId;
    String courseName;
    String courseCode;
}
