package com.landingis.api.client;

import com.landingis.api.config.FeignClientConfig;
import com.landingis.api.constant.FeignConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.lecturerscheduler.LecturerSchedulerDto;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerCreateForm;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import com.landingis.api.model.criteria.LecturerSchedulerCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(
        name = "lecturerSchedulerClient",
        url = FeignConstant.EXTENSION_SERVICE_URL + "/api/lecturer-scheduler/",
        configuration = FeignClientConfig.class
)
public interface LecturerSchedulerClient {

    @GetMapping("/list")
    ApiMessageDto<PaginationDto<LecturerSchedulerDto>> getAll(
            @SpringQueryMap LecturerSchedulerCriteria lecturerSchedulerCriteria,
            Pageable pageable
    );

    @GetMapping("/get/{id}")
    ApiMessageDto<LecturerSchedulerDto> getById(@PathVariable("id") Long id);

    @PostMapping("/create")
    ApiMessageDto<LecturerSchedulerDto> createLecturerScheduler(@Valid @RequestBody LecturerSchedulerCreateForm form);

    @PutMapping("/update/{id}")
    ApiMessageDto<LecturerSchedulerDto> updateLecturerScheduler(@PathVariable("id") Long id,
                                                                @Valid @RequestBody LecturerSchedulerUpdateForm form);

    @DeleteMapping("/delete/{id}")
    ApiMessageDto<Void> deleteLecturerScheduler(@PathVariable("id") Long id);
}
