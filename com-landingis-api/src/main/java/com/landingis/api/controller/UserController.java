package com.landingis.api.controller;

import com.landingis.api.criteria.UserCriteria;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.request.user.UserCreateRequest;
import com.landingis.api.dto.request.user.UserUpdateRequest;
import com.landingis.api.dto.response.user.UserResponse;
import com.landingis.api.service.UserService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ApiMessageDto<List<UserResponse>>> getAllUsers() {
        ApiMessageDto<List<UserResponse>> response = ApiMessageUtils
                .success(userService.getAll(),"Successfully retrieved all users");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiMessageDto<UserResponse>> getUserById(@PathVariable Long id) {
        ApiMessageDto<UserResponse> response = ApiMessageUtils
                .success(userService.getOne(id), "Successfully retrieved user by id");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/user")
    public ResponseEntity<ApiMessageDto<UserResponse>> createUser(@Validated @RequestBody UserCreateRequest request) {
        ApiMessageDto<UserResponse> response = ApiMessageUtils
                .success(userService.create(request), "User created successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiMessageDto<UserResponse>> updateUser(@PathVariable Long id,
                                                          @Validated @RequestBody UserUpdateRequest request) {
        ApiMessageDto<UserResponse> response = ApiMessageUtils
                .success(userService.update(id, request), "User updated successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiMessageDto<Void>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "User deleted successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/pagination")
    public ResponseEntity<ApiMessageDto<PaginationDto<UserResponse>>> getUsersPagination(
            UserCriteria userCriteria, Pageable pageable
    ) {

        PaginationDto<UserResponse> users = userService.getUsersPagination(userCriteria, pageable);
        ApiMessageDto<PaginationDto<UserResponse>> response = ApiMessageUtils
                .success(users, "Successfully retrieved users with pagination");

        return ResponseEntity.ok(response);
    }
}
