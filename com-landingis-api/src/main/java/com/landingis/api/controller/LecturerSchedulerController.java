package com.landingis.api.controller;

import com.landingis.api.client.LecturerSchedulerClient;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.lecturerscheduler.LecturerSchedulerDto;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerCreateForm;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lecturer-scheduler")
public class LecturerSchedulerController {

    @Autowired
    private LecturerSchedulerClient lecturerSchedulerClient;

    @PostMapping("/create")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> createLecturerScheduler(
            @Valid @RequestBody LecturerSchedulerCreateForm form
    ) {
        return ResponseEntity.ok(lecturerSchedulerClient.createLecturerScheduler(form));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> updateLecturerScheduler(
            @Valid @RequestBody LecturerSchedulerUpdateForm form
    ) {
        return ResponseEntity.ok(lecturerSchedulerClient.updateLecturerScheduler(form));
    }
}
