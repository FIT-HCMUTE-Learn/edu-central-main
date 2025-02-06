package com.landingis.api.service;

import com.landingis.api.criteria.CourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.request.course.CourseCreateRequest;
import com.landingis.api.dto.request.course.CourseUpdateRequest;
import com.landingis.api.dto.response.course.CourseResponse;
import com.landingis.api.entity.Course;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAll();
    PaginationDto<CourseResponse> getCoursesPagination(CourseCriteria courseCriteria, Pageable pageable);
    CourseResponse getOne(Long id);
    CourseResponse create(CourseCreateRequest request);
    CourseResponse update(Long id, CourseUpdateRequest request);
    void delete(Long id);
    Course findCourseById(Long id);
}
