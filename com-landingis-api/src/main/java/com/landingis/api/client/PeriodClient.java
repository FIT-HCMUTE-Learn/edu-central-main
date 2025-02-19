package com.landingis.api.client;

import com.landingis.api.config.FeignClientConfig;
import com.landingis.api.constant.FeignConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.period.PeriodDto;
import com.landingis.api.model.criteria.PeriodCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "periodClient",
        url = FeignConstant.EXTENSION_SERVICE_URL + "/api/period",
        configuration = FeignClientConfig.class
)
public interface PeriodClient {

    @GetMapping("/list")
    ApiMessageDto<PaginationDto<PeriodDto>> getAllPeriods(
            @SpringQueryMap PeriodCriteria periodCriteria,
            Pageable pageable
    );
}
