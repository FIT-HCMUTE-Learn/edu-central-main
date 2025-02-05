package com.landingis.api.mapper;

import com.landingis.api.dto.request.course.CourseCreateRequest;
import com.landingis.api.dto.request.course.CourseUpdateRequest;
import com.landingis.api.dto.response.course.CourseResponse;
import com.landingis.api.entity.Course;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mappings({
            @Mapping(source = "courseName", target = "name"),
            @Mapping(source = "courseCode", target = "code")
    })
    Course toEntity(CourseCreateRequest request);

    @Mappings({
            @Mapping(source = "courseName", target = "name"),
            @Mapping(source = "courseCode", target = "code"),
            @Mapping(target = "userCourses", ignore = true)
    })
    void updateEntity(@MappingTarget Course course, CourseUpdateRequest request);

    @Mappings({
            @Mapping(source = "id", target = "courseId"),
            @Mapping(source = "name", target = "courseName"),
            @Mapping(source = "code", target = "courseCode"),
            @Mapping(target = "userCourses", ignore = true)
    })
    CourseResponse toResponse(Course course);

    @IterableMapping(elementTargetType = CourseResponse.class)
    List<CourseResponse> toResponseList(List<Course> courses);
}
