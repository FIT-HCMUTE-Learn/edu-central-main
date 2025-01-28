package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import com.landingis.api.service.UserService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ApiMessageDto<List<User>>> getAllUsers() {
        ApiMessageDto<List<User>> response = ApiMessageUtils
                .success(userService.findAll(),"Successfully retrieved all users");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiMessageDto<User>> getUserById(@PathVariable Long id) {
        ApiMessageDto<User> response = ApiMessageUtils
                .success(userService.findById(id), "Successfully retrieved user by id");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}/courses")
    public ResponseEntity<ApiMessageDto<List<Course>>> getCourseByUserId(@PathVariable Long id){
        ApiMessageDto<List<Course>> response = ApiMessageUtils
                .success(userService.findById(id).getCourses(), "Successfully retrieved courses by user id");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user")
    public ResponseEntity<ApiMessageDto<User>> createUser(@Validated @RequestBody User user) {
        ApiMessageDto<User> response = ApiMessageUtils
                .success(userService.create(user), "User created successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiMessageDto<User>> updateUser(@PathVariable Long id, @Validated @RequestBody User user) {
        ApiMessageDto<User> response = ApiMessageUtils
                .success(userService.update(id, user), "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{userId}/register-course/{courseId}")
    public ResponseEntity<ApiMessageDto<Void>> registerCourse(@PathVariable Long userId, @PathVariable Long courseId){
        userService.registerCourse(userId, courseId);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "User register course successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiMessageDto<Void>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
