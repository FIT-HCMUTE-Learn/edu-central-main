package com.landingis.api.dto.response.intermediary;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    Long userCourseId;
    String userId;
    String courseId;
    String dateRegister;
    String status;
}
