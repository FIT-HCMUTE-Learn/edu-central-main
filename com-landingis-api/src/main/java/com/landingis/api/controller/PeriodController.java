package com.landingis.api.controller;

import com.landingis.api.client.PeriodClient;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.period.PeriodDto;
import com.landingis.api.form.period.PeriodCreateForm;
import com.landingis.api.form.period.PeriodUpdateForm;
import com.landingis.api.model.criteria.PeriodCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.landingis.api.dto.ApiMessageDto;

import javax.validation.Valid;

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

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiMessageDto<PeriodDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(periodClient.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiMessageDto<PeriodDto>> createPeriod(@Valid @RequestBody PeriodCreateForm form) {
        return ResponseEntity.ok(periodClient.createPeriod(form));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiMessageDto<PeriodDto>> updatePeriod(@PathVariable Long id, @Valid @RequestBody PeriodUpdateForm form) {
        return ResponseEntity.ok(periodClient.updatePeriod(id, form));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> deletePeriod(@PathVariable Long id) {
        return ResponseEntity.ok(periodClient.deletePeriod(id));
    }
}
