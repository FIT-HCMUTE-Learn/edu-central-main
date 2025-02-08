package com.landingis.api.dto.intermediary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCourseDto {
    private Long userCourseId;
    private UserDto user;
    private CourseDto course;
    private String dateRegister;
    private String registerStatus;
    private String completionStatus;
}
