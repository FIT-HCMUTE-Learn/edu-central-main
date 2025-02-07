package com.landingis.api.dto.response.intermediary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.landingis.api.dto.response.course.CourseDtoResponse;
import com.landingis.api.dto.response.user.UserDtoResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCourseDtoResponse {
    private Long userCourseId;
    private UserDtoResponse user;
    private CourseDtoResponse course;
    private String dateRegister;
    private String status;
}
