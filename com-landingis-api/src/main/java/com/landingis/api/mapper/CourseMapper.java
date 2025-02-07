package com.landingis.api.mapper;

import com.landingis.api.dto.response.course.CourseDtoResponse;
import com.landingis.api.form.course.CourseCreateForm;
import com.landingis.api.form.course.CourseUpdateForm;
import com.landingis.api.entity.Course;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mappings({
            @Mapping(source = "courseName", target = "name"),
            @Mapping(source = "courseCode", target = "code")
    })
    Course toEntity(CourseCreateForm request);

    @Mappings({
            @Mapping(source = "courseName", target = "name"),
            @Mapping(source = "courseCode", target = "code")
    })
    void updateEntity(@MappingTarget Course course, CourseUpdateForm request);

    @Mappings({
            @Mapping(source = "id", target = "courseId"),
            @Mapping(source = "name", target = "courseName"),
            @Mapping(source = "code", target = "courseCode")
    })
    @Named("mapCourseToDto")
    CourseDtoResponse toResponse(Course course);

    @IterableMapping(elementTargetType = CourseDtoResponse.class, qualifiedByName = "mapCourseToDto")
    List<CourseDtoResponse> toResponseList(List<Course> courses);
}
