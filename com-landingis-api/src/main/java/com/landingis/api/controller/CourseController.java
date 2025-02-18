package com.landingis.api.controller;

import com.landingis.api.dto.course.CourseAcademicReportDto;
import com.landingis.api.model.criteria.CourseCriteria;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.form.course.CourseCreateForm;
import com.landingis.api.form.course.CourseUpdateForm;
import com.landingis.api.projection.CourseAcademicReportProjection;
import com.landingis.api.service.CourseService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/academic-report/projection")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<CourseAcademicReportProjection>> getCourseAcademicReport() {
        ApiMessageDto<CourseAcademicReportProjection> response = ApiMessageUtils
                .success(courseService.getAcademicReportProjection(), "Successfully retrieved academic report with projection");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/academic-report")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<CourseAcademicReportDto>> getAcademicReport() {
        ApiMessageDto<CourseAcademicReportDto> response = ApiMessageUtils
                .success(courseService.getAcademicReport(), "Successfully retrieved academic report");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<CourseDto>>> getCoursesPagination(
            CourseCriteria courseCriteria, Pageable pageable
    ) {

        PaginationDto<CourseDto> courses = courseService.getCoursesPagination(courseCriteria, pageable);
        ApiMessageDto<PaginationDto<CourseDto>> response = ApiMessageUtils
                .success(courses, "Successfully retrieved courses with pagination");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-by-user/{userId}")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<CourseDto>>> getCoursesByUser(
            @PathVariable Long userId,
            CourseCriteria courseCriteria,
            Pageable pageable) {

        courseCriteria.setUserId(userId);
        PaginationDto<CourseDto> courses = courseService.getCoursesPagination(courseCriteria, pageable);
        ApiMessageDto<PaginationDto<CourseDto>> response = ApiMessageUtils
                .success(courses, "Successfully retrieved courses by user with pagination");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<List<CourseDto>>> getAllCourses() {
        ApiMessageDto<List<CourseDto>> response = ApiMessageUtils
                .success(courseService.getAll(), "Successfully retrieved all courses");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<CourseDto>> getCourseById(@PathVariable Long id) {
        ApiMessageDto<CourseDto> response = ApiMessageUtils
                .success(courseService.getOne(id), "Successfully retrieved course by id");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('C_POST')")
    public ResponseEntity<ApiMessageDto<CourseDto>> createCourse(@Valid @RequestBody CourseCreateForm request) {
        ApiMessageDto<CourseDto> response = ApiMessageUtils
                .success(courseService.create(request), "Course created successfully");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('C_PUT')")
    public ResponseEntity<ApiMessageDto<CourseDto>> updateCourse(@Valid @RequestBody CourseUpdateForm request) {
        ApiMessageDto<CourseDto> response = ApiMessageUtils
                .success(courseService.update(request), "Course updated successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course deleted successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-with-data-source/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deleteCourseWithDataSource(@PathVariable Long id) {
        courseService.deleteWithDataSource(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Course deleted successfully");

        return ResponseEntity.ok(response);
    }
}
