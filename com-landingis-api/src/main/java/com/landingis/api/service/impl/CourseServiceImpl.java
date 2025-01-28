package com.landingis.api.service.impl;

import com.landingis.api.entity.Course;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.repository.CourseRepository;
import com.landingis.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Course with id " + id + " not found")
        );
    }

    @Override
    public Course create(Course course) {
        if (courseRepository.existsByCode(course.getCode())) {
            throw new BusinessException("Course with code " + course.getCode() + " already exists");
        }

        return courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course courseUpdate) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Course with id " + id + " not found")
        );

        course.setName(courseUpdate.getName());
        course.setCode(courseUpdate.getCode());

        return courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Course with id " + id + " not found")
        );

        courseRepository.delete(course);
    }
}
