package com.landingis.api.mapper;

import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.form.course.CourseCreateForm;
import com.landingis.api.form.course.CourseUpdateForm;
import com.landingis.api.model.entity.Course;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    @Mappings({
            @Mapping(source = "courseName", target = "name"),
            @Mapping(source = "courseCode", target = "code")
    })
    Course toEntity(CourseCreateForm form);

    @Mappings({
            @Mapping(source = "courseName", target = "name"),
            @Mapping(source = "courseCode", target = "code")
    })
    void updateEntity(@MappingTarget Course course, CourseUpdateForm form);

    @Mappings({
            @Mapping(source = "id", target = "courseId"),
            @Mapping(source = "name", target = "courseName"),
            @Mapping(source = "code", target = "courseCode"),
            @Mapping(source = "status", target = "courseStatus")
    })
    @Named("mapCourseToDto")
    CourseDto toDto(Course course);

    @IterableMapping(elementTargetType = CourseDto.class, qualifiedByName = "mapCourseToDto")
    List<CourseDto> toDtoList(List<Course> courses);
}
