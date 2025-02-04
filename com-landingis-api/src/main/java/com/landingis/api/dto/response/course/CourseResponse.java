package com.landingis.api.dto.response.course;

import com.fasterxml.jackson.annotation.JsonView;
import com.landingis.api.dto.response.user.UserResponse;
import com.landingis.api.view.JsonViews;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CourseResponse {

    @JsonView(JsonViews.CourseView.class)
    Long courseId;

    @JsonView(JsonViews.CourseView.class)
    String courseName;

    @JsonView(JsonViews.CourseView.class)
    String courseCode;

    @JsonView(JsonViews.CourseView.class)
    List<UserResponse> users;
}
