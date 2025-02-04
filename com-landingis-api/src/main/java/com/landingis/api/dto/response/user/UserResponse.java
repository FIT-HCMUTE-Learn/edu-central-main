package com.landingis.api.dto.response.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.landingis.api.dto.response.course.CourseResponse;
import com.landingis.api.view.JsonViews;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserResponse {

    @JsonView(JsonViews.UserView.class)
    Long userId;

    @JsonView(JsonViews.UserView.class)
    String handle;

    @JsonView(JsonViews.UserView.class)
    String userPassword;

    @JsonView(JsonViews.UserView.class)
    String userFullName;

    @JsonView(JsonViews.UserView.class)
    Date userBirthday;

    @JsonView(JsonViews.UserView.class)
    List<CourseResponse> courses;
}
