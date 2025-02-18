package com.landingis.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.period.PeriodDto;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "periodClient", url = "http://localhost:8383/api/period")
public interface PeriodClient {

    @GetMapping("/list")
    ResponseEntity<ApiMessageDto<List<PeriodDto>>> getAllPeriods(@RequestHeader("Authorization") String authHeader);
}
