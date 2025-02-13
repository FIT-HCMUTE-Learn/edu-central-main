package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.student.StudentDto;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.mapper.StudentMapper;
import com.landingis.api.model.Student;
import com.landingis.api.repository.StudentRepository;
import com.landingis.api.security.CustomUserDetails;
import com.landingis.api.service.StudentService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/profile")
    public ResponseEntity<ApiMessageDto<StudentDto>> getStudentProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ApiMessageDto<StudentDto> response = ApiMessageUtils
                .success(studentService.getOneByUserId(userDetails.getUserId()), "Successfully retrieved student profile");

        return ResponseEntity.ok(response);
    }
}

