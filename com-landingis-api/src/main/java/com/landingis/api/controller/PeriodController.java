package com.landingis.api.controller;

import com.landingis.api.client.PeriodClient;
import com.landingis.api.util.FeignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.period.PeriodDto;
import java.util.List;

@RestController
@RequestMapping("/api/period")
public class PeriodController {

    @Autowired
    private PeriodClient periodClient;

    @Autowired
    private FeignUtils feignUtils;

    @GetMapping("/list")
    public ResponseEntity<ApiMessageDto<List<PeriodDto>>> getPeriodsFromExtension() {
        return periodClient.getAllPeriods(feignUtils.getBasicAuthHeader());
    }
}
