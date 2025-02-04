package com.landingis.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.request.course.CourseCreateRequest;
import com.landingis.api.dto.request.course.CourseUpdateRequest;
import com.landingis.api.dto.response.course.CourseResponse;
import com.landingis.api.dto.response.user.UserResponse;
import com.landingis.api.service.CourseService;
import com.landingis.api.util.ApiMessageUtils;
import com.landingis.api.view.JsonViews;
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
    @JsonView(JsonViews.CourseView.class)
    public ResponseEntity<ApiMessageDto<List<CourseResponse>>> getAllCourses() {
        ApiMessageDto<List<CourseResponse>> response = ApiMessageUtils
                .success(courseService.findAll(), "Successfully retrieved all courses");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{id}")
    @JsonView(JsonViews.CourseView.class)
    public ResponseEntity<ApiMessageDto<CourseResponse>> getCourseById(@PathVariable Long id) {
        ApiMessageDto<CourseResponse> response = ApiMessageUtils
                .success(courseService.findById(id), "Successfully retrieved course by id");

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

    @GetMapping("/course/{id}/users")
    @JsonView(JsonViews.UserView.class)
    public ResponseEntity<ApiMessageDto<List<UserResponse>>> getUsersByUserId(@PathVariable Long id){
        ApiMessageDto<List<UserResponse>> response = ApiMessageUtils
                .success(courseService.findById(id).getUsers(), "Successfully retrieved users by course id");

        return ResponseEntity.ok(response);
    }
}
