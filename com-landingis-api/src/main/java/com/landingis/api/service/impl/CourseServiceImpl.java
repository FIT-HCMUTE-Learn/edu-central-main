package com.landingis.api.service.impl;

import com.landingis.api.dto.course.CourseAcademicReportDto;
import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.model.criteria.CourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.form.course.CourseCreateForm;
import com.landingis.api.form.course.CourseUpdateForm;
import com.landingis.api.model.Course;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.mapper.CourseMapper;
import com.landingis.api.repository.CourseRepository;
import com.landingis.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CourseDto> getAll() {
        return courseMapper.toDtoList(courseRepository.findAll());
    }

    @Override
    public PaginationDto<CourseDto> getCoursesPagination(CourseCriteria courseCriteria, Pageable pageable) {
        Specification<Course> spec = courseCriteria.getSpecification();
        Page<Course> coursePage = courseRepository.findAll(spec, pageable);

        return new PaginationDto<>(
                courseMapper.toDtoList(coursePage.getContent()),
                coursePage.getTotalElements(),
                coursePage.getTotalPages()
        );
    }

    @Override
    public CourseDto getOne(Long id) {
        Course course = findCourseById(id);
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto create(CourseCreateForm request) {
        Course course = courseMapper.toEntity(request);

        if (courseRepository.existsByCode(course.getCode())) {
            throw new BusinessException("Course with code " + course.getCode() + " already exists");
        }

        Course savedCourse = courseRepository.save(course);

        return courseMapper.toDto(savedCourse);
    }

    @Override
    public CourseDto update(CourseUpdateForm request) {
        Long id = request.getCourseId();
        Course course = findCourseById(id);

        if (!Objects.equals(request.getCourseCode(), course.getCode()) && courseRepository.existsByCode(request.getCourseCode())) {
            throw new BusinessException("Course with code " + request.getCourseCode() + " already exists");
        }

        courseMapper.updateEntity(course, request);
        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toDto(updatedCourse);
    }

    @Override
    public void delete(Long id) {
        Course course = findCourseById(id);
        courseRepository.delete(course);
    }

    @Override
    public void deleteWithDataSource(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Course with id " + id + " not found")
        );
    }

    @Override
    public CourseAcademicReportDto getAcademicReport() {
        return courseRepository.getAcademicReport();
    }
}
