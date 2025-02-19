package com.landingis.api.client;

import com.landingis.api.config.FeignClientConfig;
import com.landingis.api.constant.FeignConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.lecturerscheduler.LecturerSchedulerDto;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerCreateForm;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(
        name = "lecturerSchedulerClient",
        url = FeignConstant.EXTENSION_SERVICE_URL + "/api/lecturer-scheduler/",
        configuration = FeignClientConfig.class
)
public interface LecturerSchedulerClient {

    @PostMapping("/create")
    ApiMessageDto<LecturerSchedulerDto> createLecturerScheduler(
            LecturerSchedulerCreateForm form
    );

    @PutMapping("/update")
    ApiMessageDto<LecturerSchedulerDto> updateLecturerScheduler(
            LecturerSchedulerUpdateForm form
    );
}
