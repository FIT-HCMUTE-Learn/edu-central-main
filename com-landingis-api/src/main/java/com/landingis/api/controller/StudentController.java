package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.student.StudentDto;
import com.landingis.api.form.student.StudentCreateForm;
import com.landingis.api.model.criteria.StudentCriteria;
import com.landingis.api.security.CustomUserDetails;
import com.landingis.api.service.StudentService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/profile")
    public ResponseEntity<ApiMessageDto<StudentDto>> getStudentProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ApiMessageDto<StudentDto> response = ApiMessageUtils
                .success(studentService.getStudentById(userDetails.getUserId()), "Successfully retrieved student profile");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<StudentDto>>> getStudentsPagination(
            StudentCriteria studentCriteria, Pageable pageable) {

        PaginationDto<StudentDto> students = studentService.getStudentsPagination(studentCriteria, pageable);
        ApiMessageDto<PaginationDto<StudentDto>> response = ApiMessageUtils
                .success(students, "Successfully retrieved students with pagination");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('C_POST')")
    public ResponseEntity<ApiMessageDto<StudentDto>> createStudent(@Valid @RequestBody StudentCreateForm form) {
        ApiMessageDto<StudentDto> response = ApiMessageUtils
                .success(studentService.createStudent(form), "Student created successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('C_PUT')")
    public ResponseEntity<ApiMessageDto<StudentDto>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentCreateForm form) {
        ApiMessageDto<StudentDto> response = ApiMessageUtils
                .success(studentService.updateStudent(id, form), "Student updated successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiMessageUtils.success(null, "Student deleted successfully"));
    }
}

