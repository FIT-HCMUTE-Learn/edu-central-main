package com.landingis.api.service.impl;

import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.admin.AdminDto;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.form.admin.AdminCreateForm;
import com.landingis.api.form.admin.AdminUpdateForm;
import com.landingis.api.mapper.AdminMapper;
import com.landingis.api.model.Admin;
import com.landingis.api.model.Group;
import com.landingis.api.model.User;
import com.landingis.api.model.criteria.AdminCriteria;
import com.landingis.api.repository.AdminRepository;
import com.landingis.api.repository.GroupRepository;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.landingis.api.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AdminDto createAdmin(AdminCreateForm form) {
        if (userRepository.existsByUsername(form.getUserHandle())) {
            throw new BusinessException("Username already exists");
        }

        User user = new User();
        user.setUsername(form.getUserHandle());
        user.setPassword(passwordEncoder.encode(form.getUserPassword()));
        user.setFullName(form.getUserFullName());
        user.setGender(form.getUserGender());

        Group group = groupRepository.findGroupByName("GROUP_ADMIN");
        user.setGroup(group);

        User savedUser = userRepository.save(user);

        Admin admin = new Admin();
        admin.setLevel(form.getAdminLevel());
        admin.setIsSuperAdmin(form.getIsSuperAdmin());
        admin.setUser(savedUser);

        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.toDto(savedAdmin);
    }

    @Override
    @Transactional
    public AdminDto updateAdmin(Long id, AdminUpdateForm form) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        User user = admin.getUser();
        user.setFullName(form.getUserFullName());
        user.setGender(form.getUserGender());
        userRepository.save(user);

        admin.setLevel(form.getAdminLevel());
        admin.setIsSuperAdmin(form.getIsSuperAdmin());
        Admin updatedAdmin = adminRepository.save(admin);

        return adminMapper.toDto(updatedAdmin);
    }

    @Override
    @Transactional
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @Override
    public AdminDto getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        return adminMapper.toDto(admin);
    }

    @Override
    public PaginationDto<AdminDto> getAdminsPagination(AdminCriteria adminCriteria, Pageable pageable) {
        Specification<Admin> spec = adminCriteria.getSpecification();
        Page<Admin> adminsPage = adminRepository.findAll(spec, pageable);

        return new PaginationDto<>(
                adminMapper.toDtoList(adminsPage.getContent()),
                adminsPage.getTotalElements(),
                adminsPage.getTotalPages()
        );
    }

    @Override
    public boolean checkIsSuperAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getIsSuperAdmin();
        }

        return false;
    }
}

