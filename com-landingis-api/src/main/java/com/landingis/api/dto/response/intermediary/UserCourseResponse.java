package com.landingis.api.dto.response.intermediary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.landingis.api.composite.UserCourseId;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserCourseResponse {
    UserCourseId userCourseId;
    User user;
    Course course;
    String dateRegister;
    String status;
}
