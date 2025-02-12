package com.landingis.api.service;

import com.landingis.api.dto.user.UserDto;
import com.landingis.api.model.criteria.UserCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.form.user.UserCreateForm;
import com.landingis.api.form.user.UserUpdateForm;
import com.landingis.api.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    PaginationDto<UserDto> getUsersPagination(UserCriteria userCriteria, Pageable pageable);
    UserDto getOne(Long id);
    UserDto create(UserCreateForm request);
    UserDto update(UserUpdateForm request);
    void delete(Long id);
    void deleteWithDataSource(Long id);
    User findUserById(Long id);
}
