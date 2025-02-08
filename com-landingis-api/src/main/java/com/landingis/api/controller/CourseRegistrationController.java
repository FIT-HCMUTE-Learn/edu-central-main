package com.landingis.api.controller;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.entity.criteria.UserCourseCriteria;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.service.UserCourseService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class CourseRegistrationController {

    @Autowired
    private UserCourseService userCourseService;

    @GetMapping("/list")
    public ResponseEntity<ApiMessageDto<PaginationDto<UserCourseDto>>> getUserCourses(
            UserCourseCriteria userCourseCriteria,
            Pageable pageable
    ) {

        PaginationDto<UserCourseDto> userCourses = userCourseService.getUserCoursesPagination(userCourseCriteria, pageable);
        ApiMessageDto<PaginationDto<UserCourseDto>> response = ApiMessageUtils
                .success(userCourses, "Successfully retrieved course registration with pagination");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/{userId}/{courseId}")
    public ResponseEntity<ApiMessageDto<UserCourseDto>> registerCourse(@PathVariable Long userId,
                                                                       @PathVariable Long courseId) {
        ApiMessageDto<UserCourseDto> response = ApiMessageUtils
                .success(userCourseService.registerCourse(userId, courseId), "Course registered successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/unregister/{userId}/{courseId}")
    public ResponseEntity<ApiMessageDto<Void>> unregisterCourse(@PathVariable Long userId,
                                                                @PathVariable Long courseId) {
        userCourseService.unregisterCourse(userId, courseId);

        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course unregistered successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{userId}/{courseId}/status")
    public ResponseEntity<ApiMessageDto<Void>> updateStatus(@PathVariable Long userId,
                                                            @PathVariable Long courseId,
                                                            @RequestParam RegisterStatus status) {
        userCourseService.updateStatus(userId, courseId, status);

        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Status updated successfully");

        return ResponseEntity.ok(response);
    }
}

