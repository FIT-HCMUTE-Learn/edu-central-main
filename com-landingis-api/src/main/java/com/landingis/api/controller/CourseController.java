package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
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
    public ResponseEntity<ApiMessageDto<List<Course>>> getAllCourses() {
        ApiMessageDto<List<Course>> response = ApiMessageUtils
                .success(courseService.findAll(), "Successfully retrieved all courses");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<ApiMessageDto<Course>> getCourseById(@PathVariable Long id) {
        ApiMessageDto<Course> response = ApiMessageUtils
                .success(courseService.findById(id), "Successfully retrieved course by id");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{id}/users")
    public ResponseEntity<ApiMessageDto<List<User>>> getUsersByUserId(@PathVariable Long id){
        ApiMessageDto<List<User>> response = ApiMessageUtils
                .success(courseService.findById(id).getUsers(), "Successfully retrieved users by course id");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/course")
    public ResponseEntity<ApiMessageDto<Course>> createCourse(@Validated @RequestBody Course course) {
        ApiMessageDto<Course> response = ApiMessageUtils
                .success(courseService.create(course), "Course created successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<ApiMessageDto<Course>> updateCourse(@PathVariable Long id, @Validated @RequestBody Course course) {
        ApiMessageDto<Course> response = ApiMessageUtils
                .success(courseService.update(id, course), "Course updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<ApiMessageDto<Void>> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course deleted successfully");
        return ResponseEntity.ok(response);
    }
}
