package com.landingis.api.service.impl;

import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.request.course.CourseCreateRequest;
import com.landingis.api.dto.request.course.CourseUpdateRequest;
import com.landingis.api.dto.response.course.CourseResponse;
import com.landingis.api.entity.Course;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.mapper.CourseMapper;
import com.landingis.api.repository.CourseRepository;
import com.landingis.api.repository.criteria.CourseCriteriaRepository;
import com.landingis.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseCriteriaRepository courseCriteriaRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseResponse> getAll() {
        return courseMapper.toResponseList(courseRepository.findAll());
    }

    @Override
    public PaginationDto<CourseResponse> getCoursesPagination(String name, String code, int page, int size) {
        List<Course> courses = courseCriteriaRepository.findCourses(name, code, page, size);
        long totalElements = courseCriteriaRepository.countCourses(name, code);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return new PaginationDto<>(courseMapper.toResponseList(courses), totalElements, totalPages);
    }

    @Override
    public CourseResponse getOne(Long id) {
        Course course = findCourseById(id);

        return courseMapper.toResponse(course);
    }

    @Override
    public CourseResponse create(CourseCreateRequest request) {
        Course course = courseMapper.toEntity(request);

        if (courseRepository.existsByCode(course.getCode())) {
            throw new BusinessException("Course with code " + course.getCode() + " already exists");
        }

        Course savedCourse = courseRepository.save(course);

        return courseMapper.toResponse(savedCourse);
    }

    @Override
    public CourseResponse update(Long id, CourseUpdateRequest request) {
        Course course = findCourseById(id);

        courseMapper.updateEntity(course, request);

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponse(updatedCourse);
    }

    @Override
    public void delete(Long id) {
        Course course = findCourseById(id);

        courseRepository.delete(course);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Course with id " + id + " not found")
        );
    }
}
