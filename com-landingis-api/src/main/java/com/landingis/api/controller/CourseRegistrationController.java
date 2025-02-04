package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.service.UserCourseService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class CourseRegistrationController {

    @Autowired
    private UserCourseService userCourseService;

    @PostMapping("/{userId}/{courseId}")
    public ResponseEntity<ApiMessageDto<UserCourse>> registerCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        ApiMessageDto<UserCourse> response = ApiMessageUtils
                .success(userCourseService.registerCourse(userId, courseId), "Course registered successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/{courseId}")
    public ResponseEntity<ApiMessageDto<Void>> unregisterCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        userCourseService.unregisterCourse(userId, courseId);

        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course unregistered successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/{courseId}/status")
    public ResponseEntity<ApiMessageDto<Void>> updateStatus(@PathVariable Long userId,
                                                   @PathVariable Long courseId,
                                                   @RequestParam RegisterStatus status) {
        userCourseService.updateStatus(userId, courseId, status);

        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Status updated successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiMessageDto<List<UserCourse>>> getCoursesByUser(@PathVariable Long userId) {
        ApiMessageDto<List<UserCourse>> response = ApiMessageUtils
                .success(userCourseService.getUserCoursesByUserId(userId), "Courses retrieved successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiMessageDto<List<UserCourse>>> getUsersByCourse(@PathVariable Long courseId) {
        ApiMessageDto<List<UserCourse>> response = ApiMessageUtils
                .success(userCourseService.getUserCoursesByCourseId(courseId), "Users retrieved successfully");

        return ResponseEntity.ok(response);
    }
}

