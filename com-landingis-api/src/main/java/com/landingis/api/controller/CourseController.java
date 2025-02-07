package com.landingis.api.controller;

import com.landingis.api.entity.criteria.CourseCriteria;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.response.course.CourseDtoResponse;
import com.landingis.api.form.course.CourseCreateForm;
import com.landingis.api.form.course.CourseUpdateForm;
import com.landingis.api.service.CourseService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<ApiMessageDto<PaginationDto<CourseDtoResponse>>> getCoursesPagination(
            CourseCriteria courseCriteria, Pageable pageable
    ) {

        PaginationDto<CourseDtoResponse> courses = courseService.getCoursesPagination(courseCriteria, pageable);
        ApiMessageDto<PaginationDto<CourseDtoResponse>> response = ApiMessageUtils
                .success(courses, "Successfully retrieved courses with pagination");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-by-user/{userId}")
    public ResponseEntity<ApiMessageDto<PaginationDto<CourseDtoResponse>>> getCoursesByUser(
            @PathVariable Long userId,
            CourseCriteria courseCriteria,
            Pageable pageable) {

        courseCriteria.setUserId(userId);
        PaginationDto<CourseDtoResponse> courses = courseService.getCoursesPagination(courseCriteria, pageable);
        ApiMessageDto<PaginationDto<CourseDtoResponse>> response = ApiMessageUtils
                .success(courses, "Successfully retrieved courses by user with pagination");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    public ResponseEntity<ApiMessageDto<List<CourseDtoResponse>>> getAllCourses() {
        ApiMessageDto<List<CourseDtoResponse>> response = ApiMessageUtils
                .success(courseService.getAll(), "Successfully retrieved all courses");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiMessageDto<CourseDtoResponse>> getCourseById(@PathVariable Long id) {
        ApiMessageDto<CourseDtoResponse> response = ApiMessageUtils
                .success(courseService.getOne(id), "Successfully retrieved course by id");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiMessageDto<CourseDtoResponse>> createCourse(@Validated @RequestBody CourseCreateForm request) {
        ApiMessageDto<CourseDtoResponse> response = ApiMessageUtils
                .success(courseService.create(request), "Course created successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiMessageDto<CourseDtoResponse>> updateCourse(@PathVariable Long id,
                                                                         @Validated @RequestBody CourseUpdateForm request) {
        ApiMessageDto<CourseDtoResponse> response = ApiMessageUtils
                .success(courseService.update(id, request), "Course updated successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course deleted successfully");

        return ResponseEntity.ok(response);
    }
}
