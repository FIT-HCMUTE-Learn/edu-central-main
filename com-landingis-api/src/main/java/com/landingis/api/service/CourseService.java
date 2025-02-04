package com.landingis.api.service;

import com.landingis.api.dto.request.course.CourseCreateRequest;
import com.landingis.api.dto.request.course.CourseUpdateRequest;
import com.landingis.api.dto.response.course.CourseResponse;
import com.landingis.api.entity.Course;

import java.util.List;

public interface CourseService {
    List<CourseResponse> findAll();
    CourseResponse findById(Long id);
    CourseResponse create(CourseCreateRequest request);
    CourseResponse update(Long id, CourseUpdateRequest request);
    void delete(Long id);
    Course findCourseById(Long id);
}
