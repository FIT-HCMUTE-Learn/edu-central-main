package com.landingis.api.mapper;

import com.landingis.api.dto.response.intermediary.UserCourseResponse;
import com.landingis.api.entity.UserCourse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserCourseMapper {
    @Mappings({
            @Mapping(source = "id", target = "userCourseId"),
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "course.id", target = "courseId"),
            @Mapping(source = "dateRegister", target = "dateRegister"),
            @Mapping(source = "status", target = "status")
    })
    UserCourseResponse toResponse(UserCourse userCourse);

    @IterableMapping(elementTargetType = UserCourseResponse.class)
    List<UserCourseResponse> toResponseList(List<UserCourse> userCourses);
}
