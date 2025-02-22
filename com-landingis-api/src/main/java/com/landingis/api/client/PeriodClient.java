package com.landingis.api.client;

import com.landingis.api.config.FeignClientConfig;
import com.landingis.api.constant.FeignConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.period.PeriodDto;
import com.landingis.api.form.period.PeriodCreateForm;
import com.landingis.api.form.period.PeriodUpdateForm;
import com.landingis.api.model.criteria.PeriodCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(
        name = "periodClient",
        url = FeignConstant.EXTENSION_SERVICE_URL + "/api/period",
        configuration = FeignClientConfig.class
)
public interface PeriodClient {

    @GetMapping("/list")
    ApiMessageDto<PaginationDto<PeriodDto>> getAllPeriods(
            @SpringQueryMap PeriodCriteria periodCriteria,
            @RequestHeader(value = "CUSTOM_PAGEABLE", required = false) String pageable
    );

    @GetMapping("/get/{id}")
    ApiMessageDto<PeriodDto> getById(@PathVariable("id") Long id);

    @PostMapping("/create")
    ApiMessageDto<PeriodDto> createPeriod(@Valid @RequestBody PeriodCreateForm form);

    @PutMapping("/update")
    ApiMessageDto<PeriodDto> updatePeriod(@Valid @RequestBody PeriodUpdateForm form);

    @DeleteMapping("/delete/{id}")
    ApiMessageDto<Void> deletePeriod(@PathVariable("id") Long id);
}
