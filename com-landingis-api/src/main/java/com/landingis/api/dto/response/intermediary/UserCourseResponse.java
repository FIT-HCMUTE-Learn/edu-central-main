package com.landingis.api.dto.response.intermediary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCourseResponse {
    private Long userCourseId;
    private User user;
    private Course course;
    private String dateRegister;
    private String status;
}
