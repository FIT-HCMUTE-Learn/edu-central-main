package com.landingis.api.service;

import com.landingis.api.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Long id);
    Course create(Course course);
    Course update(Long id, Course courseUpdate);
    void delete(Long id);
}
