package com.landingis.api.controller;

import com.landingis.api.client.LecturerSchedulerClient;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.lecturerscheduler.LecturerSchedulerDto;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerCreateForm;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import com.landingis.api.model.criteria.LecturerSchedulerCriteria;
import com.landingis.api.repository.CourseRepository;
import com.landingis.api.repository.LecturerRepository;
import com.landingis.api.util.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lecturer-scheduler")
public class LecturerSchedulerController {

    @Autowired
    private LecturerSchedulerClient lecturerSchedulerClient;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<LecturerSchedulerDto>>> getAll(
            LecturerSchedulerCriteria lecturerSchedulerCriteria,
            Pageable pageable
    ) {
        return ResponseEntity.ok(lecturerSchedulerClient.getAll(lecturerSchedulerCriteria, PageableUtils.pageableToString(pageable)));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(lecturerSchedulerClient.getById(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('C_POST')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> createLecturerScheduler(
            @Valid @RequestBody LecturerSchedulerCreateForm form
    ) {
        lecturerRepository.findLecturerById(form.getLecturerId())
                .orElseThrow(() -> new ResourceNotFoundException("Lecturer with id " + form.getLecturerId() + " not found"));

        courseRepository.findCourseById(form.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course with id " + form.getCourseId() + " not found"));

        return ResponseEntity.ok(lecturerSchedulerClient.createLecturerScheduler(form));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('C_PUT')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> updateLecturerScheduler(
            @Valid @RequestBody LecturerSchedulerUpdateForm form
    ) {
        if (form.getLecturerId() != null) {
            lecturerRepository.findLecturerById(form.getLecturerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lecturer with id " + form.getLecturerId() + " not found"));
        }
        if (form.getCourseId() != null) {
            courseRepository.findCourseById(form.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course with id " + form.getCourseId() + " not found"));
        }

        return ResponseEntity.ok(lecturerSchedulerClient.updateLecturerScheduler(form));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deleteLecturerScheduler(@PathVariable Long id) {
        return ResponseEntity.ok(lecturerSchedulerClient.deleteLecturerScheduler(id));
    }
}
