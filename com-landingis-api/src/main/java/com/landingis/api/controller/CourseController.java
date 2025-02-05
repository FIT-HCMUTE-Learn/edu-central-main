package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.request.course.CourseCreateRequest;
import com.landingis.api.dto.request.course.CourseUpdateRequest;
import com.landingis.api.dto.response.course.CourseResponse;
import com.landingis.api.dto.response.user.UserResponse;
import com.landingis.api.service.CourseService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<ApiMessageDto<List<CourseResponse>>> getAllCourses() {
        ApiMessageDto<List<CourseResponse>> response = ApiMessageUtils
                .success(courseService.getAll(), "Successfully retrieved all courses");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<ApiMessageDto<CourseResponse>> getCourseById(@PathVariable Long id) {
        ApiMessageDto<CourseResponse> response = ApiMessageUtils
                .success(courseService.getOne(id), "Successfully retrieved course by id");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/course")
    public ResponseEntity<ApiMessageDto<CourseResponse>> createCourse(@Validated @RequestBody CourseCreateRequest request) {
        ApiMessageDto<CourseResponse> response = ApiMessageUtils
                .success(courseService.create(request), "Course created successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<ApiMessageDto<CourseResponse>> updateCourse(@PathVariable Long id,
                                                              @Validated @RequestBody CourseUpdateRequest request) {
        ApiMessageDto<CourseResponse> response = ApiMessageUtils
                .success(courseService.update(id, request), "Course updated successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<ApiMessageDto<Void>> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course deleted successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/courses/pagination")
    public ResponseEntity<ApiMessageDto<PaginationDto<CourseResponse>>> getCoursesPagination(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String courseCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PaginationDto<CourseResponse> courses = courseService.getCoursesPagination(courseName, courseCode, page, size);
        ApiMessageDto<PaginationDto<CourseResponse>> response = ApiMessageUtils
                .success(courses, "List users success");

        return ResponseEntity.ok(response);
    }
}
