package com.landingis.api.controller;

import com.landingis.api.client.PeriodClient;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.period.PeriodDto;
import com.landingis.api.model.criteria.PeriodCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.landingis.api.dto.ApiMessageDto;

@RestController
@RequestMapping("/api/period")
public class PeriodController {

    @Autowired
    private PeriodClient periodClient;

    @GetMapping("/list")
    public ResponseEntity<ApiMessageDto<PaginationDto<PeriodDto>>> getPeriodsFromExtension(
            PeriodCriteria periodCriteria,
            Pageable pageable
    ) {
        return ResponseEntity.ok(periodClient.getAllPeriods(periodCriteria, pageable));
    }
}
