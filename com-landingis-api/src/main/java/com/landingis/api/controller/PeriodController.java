package com.landingis.api.controller;

import com.landingis.api.client.PeriodClient;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.period.PeriodDto;
import com.landingis.api.form.period.PeriodCreateForm;
import com.landingis.api.form.period.PeriodUpdateForm;
import com.landingis.api.model.criteria.PeriodCriteria;
import com.landingis.api.util.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.landingis.api.dto.ApiMessageDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/period")
public class PeriodController {

    @Autowired
    private PeriodClient periodClient;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<PeriodDto>>> getPeriodsFromExtension(
            PeriodCriteria periodCriteria,
            Pageable pageable
    ) {
        return ResponseEntity.ok(periodClient.getAllPeriods(periodCriteria, PageableUtils.pageableToString(pageable)));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(periodClient.getById(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('C_POST')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> createPeriod(@Valid @RequestBody PeriodCreateForm form) {
        return ResponseEntity.ok(periodClient.createPeriod(form));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('C_PUT')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> updatePeriod(@Valid @RequestBody PeriodUpdateForm form) {
        return ResponseEntity.ok(periodClient.updatePeriod(form));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deletePeriod(@PathVariable Long id) {
        return ResponseEntity.ok(periodClient.deletePeriod(id));
    }
}
