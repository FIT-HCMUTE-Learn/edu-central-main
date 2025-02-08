package com.landingis.api.service;

import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.entity.criteria.CourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.form.course.CourseCreateForm;
import com.landingis.api.form.course.CourseUpdateForm;
import com.landingis.api.entity.Course;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAll();
    PaginationDto<CourseDto> getCoursesPagination(CourseCriteria courseCriteria, Pageable pageable);
    CourseDto getOne(Long id);
    CourseDto create(CourseCreateForm request);
    CourseDto update(CourseUpdateForm request);
    void delete(Long id);
    Course findCourseById(Long id);
}
