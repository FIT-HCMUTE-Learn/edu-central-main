package com.landingis.api.controller;

import com.landingis.api.entity.criteria.UserCriteria;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.response.user.UserDtoResponse;
import com.landingis.api.form.user.UserCreateForm;
import com.landingis.api.form.user.UserUpdateForm;
import com.landingis.api.service.UserService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ApiMessageDto<PaginationDto<UserDtoResponse>>> getUsersPagination(
            UserCriteria userCriteria, Pageable pageable
    ) {

        PaginationDto<UserDtoResponse> users = userService.getUsersPagination(userCriteria, pageable);
        ApiMessageDto<PaginationDto<UserDtoResponse>> response = ApiMessageUtils
                .success(users, "Successfully retrieved users with pagination");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-by-course/{courseId}")
    public ResponseEntity<ApiMessageDto<PaginationDto<UserDtoResponse>>> getUsersByCourse(
            @PathVariable Long courseId,
            UserCriteria userCriteria,
            Pageable pageable) {

        userCriteria.setCourseId(courseId);
        PaginationDto<UserDtoResponse> users = userService.getUsersPagination(userCriteria, pageable);
        ApiMessageDto<PaginationDto<UserDtoResponse>> response = ApiMessageUtils
                .success(users, "Successfully retrieved users by course with pagination");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    public ResponseEntity<ApiMessageDto<List<UserDtoResponse>>> getAllUsers() {
        ApiMessageDto<List<UserDtoResponse>> response = ApiMessageUtils
                .success(userService.getAll(),"Successfully retrieved all users");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiMessageDto<UserDtoResponse>> getUserById(@PathVariable Long id) {
        ApiMessageDto<UserDtoResponse> response = ApiMessageUtils
                .success(userService.getOne(id), "Successfully retrieved user by id");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiMessageDto<UserDtoResponse>> createUser(@Validated @RequestBody UserCreateForm request) {
        ApiMessageDto<UserDtoResponse> response = ApiMessageUtils
                .success(userService.create(request), "User created successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiMessageDto<UserDtoResponse>> updateUser(@PathVariable Long id,
                                                                     @Validated @RequestBody UserUpdateForm request) {
        ApiMessageDto<UserDtoResponse> response = ApiMessageUtils
                .success(userService.update(id, request), "User updated successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "User deleted successfully");

        return ResponseEntity.ok(response);
    }
}
