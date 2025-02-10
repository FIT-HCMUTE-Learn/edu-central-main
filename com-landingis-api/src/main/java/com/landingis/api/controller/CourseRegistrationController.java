package com.landingis.api.controller;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.model.criteria.UserCourseCriteria;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.form.usercourse.UserCourseRegisterForm;
import com.landingis.api.form.usercourse.UserCourseUnregisterForm;
import com.landingis.api.form.usercourse.UserCourseUpdateForm;
import com.landingis.api.service.UserCourseService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/register")
    public ResponseEntity<ApiMessageDto<UserCourseDto>> registerCourse(@Valid @RequestBody UserCourseRegisterForm form) {
        ApiMessageDto<UserCourseDto> response = ApiMessageUtils
                .success(userCourseService.registerCourse(form), "Course registered successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<ApiMessageDto<Void>> unregisterCourse(@Valid @RequestBody UserCourseUnregisterForm form) {
        userCourseService.unregisterCourse(form);

        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course unregistered successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiMessageDto<UserCourseDto>> updateRegisterStatus(@Valid @RequestBody UserCourseUpdateForm form) {
        ApiMessageDto<UserCourseDto> response = ApiMessageUtils
                .success(userCourseService.update(form), "Register status updated successfully");

        return ResponseEntity.ok(response);
    }
}

