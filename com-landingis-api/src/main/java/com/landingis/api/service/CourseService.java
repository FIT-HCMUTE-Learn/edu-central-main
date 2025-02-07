package com.landingis.api.service;

import com.landingis.api.entity.criteria.CourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.response.course.CourseDtoResponse;
import com.landingis.api.form.course.CourseCreateForm;
import com.landingis.api.form.course.CourseUpdateForm;
import com.landingis.api.entity.Course;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    List<CourseDtoResponse> getAll();
    PaginationDto<CourseDtoResponse> getCoursesPagination(CourseCriteria courseCriteria, Pageable pageable);
    CourseDtoResponse getOne(Long id);
    CourseDtoResponse create(CourseCreateForm request);
    CourseDtoResponse update(Long id, CourseUpdateForm request);
    void delete(Long id);
    Course findCourseById(Long id);
}
