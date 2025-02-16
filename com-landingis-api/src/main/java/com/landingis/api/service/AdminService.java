package com.landingis.api.service;

import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.admin.AdminDto;
import com.landingis.api.form.admin.AdminCreateForm;
import com.landingis.api.form.admin.AdminUpdateForm;
import com.landingis.api.model.criteria.AdminCriteria;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    AdminDto createAdmin(AdminCreateForm form);
    AdminDto updateAdmin(Long id, AdminUpdateForm form);
    void deleteAdmin(Long id);
    AdminDto getAdminById(Long id);
    PaginationDto<AdminDto> getAdminsPagination(AdminCriteria adminCriteria, Pageable pageable);
}
