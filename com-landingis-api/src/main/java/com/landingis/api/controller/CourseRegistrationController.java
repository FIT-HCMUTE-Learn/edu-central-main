package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.response.intermediary.UserCourseResponse;
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
    public ResponseEntity<ApiMessageDto<UserCourseResponse>> registerCourse(@PathVariable Long userId,
                                                                            @PathVariable Long courseId) {
        ApiMessageDto<UserCourseResponse> response = ApiMessageUtils
                .success(userCourseService.registerCourse(userId, courseId), "Course registered successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/{courseId}")
    public ResponseEntity<ApiMessageDto<Void>> unregisterCourse(@PathVariable Long userId,
                                                                @PathVariable Long courseId) {
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

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<ApiMessageDto<UserCourseResponse>> getUserCourseResponse(@PathVariable Long userId,
                                                                                   @PathVariable Long courseId) {
        ApiMessageDto<UserCourseResponse> response = ApiMessageUtils
                .success(userCourseService.getUserCourseResponse(userId, courseId), "Course retrieved successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiMessageDto<List<UserCourseResponse>>> getCoursesByUser(@PathVariable Long userId) {
        ApiMessageDto<List<UserCourseResponse>> response = ApiMessageUtils
                .success(userCourseService.getUserCoursesByUserId(userId), "Courses retrieved successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiMessageDto<List<UserCourseResponse>>> getUsersByCourse(@PathVariable Long courseId) {
        ApiMessageDto<List<UserCourseResponse>> response = ApiMessageUtils
                .success(userCourseService.getUserCoursesByCourseId(courseId), "Users retrieved successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiMessageDto<PaginationDto<UserCourseResponse>>> getUserCourses(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nameUser,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String courseCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PaginationDto<UserCourseResponse> userCourses = userCourseService.getUserCoursesPagination(username, nameUser, courseName, courseCode, page, size);
        ApiMessageDto<PaginationDto<UserCourseResponse>> response = ApiMessageUtils
                .success(userCourses, "List user courses success");

        return ResponseEntity.ok(response);
    }
}

