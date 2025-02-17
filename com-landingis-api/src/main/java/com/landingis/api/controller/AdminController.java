package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.admin.AdminDto;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.form.admin.AdminCreateForm;
import com.landingis.api.form.admin.AdminUpdateForm;
import com.landingis.api.model.criteria.AdminCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.service.AdminService;
import com.landingis.api.service.AuthenticationService;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<AdminDto>>> getAdminsPagination(
            AdminCriteria adminCriteria, Pageable pageable
    ) {
        PaginationDto<AdminDto> admins = adminService.getAdminsPagination(adminCriteria, pageable);
        ApiMessageDto<PaginationDto<AdminDto>> response = ApiMessageUtils
                .success(admins, "Successfully retrieved admins with pagination");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<AdminDto>> getAdminById(@PathVariable Long id) {
        ApiMessageDto<AdminDto> response = ApiMessageUtils
                .success(adminService.getAdminById(id), "Successfully retrieved admin by id");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('C_POST')")
    public ResponseEntity<ApiMessageDto<AdminDto>> createAdmin(@Valid @RequestBody AdminCreateForm request) {
        if (!authenticationService.checkIsSuperAdmin()) {
            throw new BusinessException("Only super admins can create a new admin.");
        }

        ApiMessageDto<AdminDto> response = ApiMessageUtils
                .success(adminService.createAdmin(request), "Admin created successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('C_PUT')")
    public ResponseEntity<ApiMessageDto<AdminDto>> updateAdmin(@PathVariable Long id, @Valid @RequestBody AdminUpdateForm request) {
        ApiMessageDto<AdminDto> response = ApiMessageUtils
                .success(adminService.updateAdmin(id, request), "Admin updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Admin deleted successfully");
        return ResponseEntity.ok(response);
    }
}
