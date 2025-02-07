package com.landingis.api.service;

import com.landingis.api.entity.criteria.UserCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.response.user.UserDtoResponse;
import com.landingis.api.form.user.UserCreateForm;
import com.landingis.api.form.user.UserUpdateForm;
import com.landingis.api.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDtoResponse> getAll();
    PaginationDto<UserDtoResponse> getUsersPagination(UserCriteria userCriteria, Pageable pageable);
    UserDtoResponse getOne(Long id);
    UserDtoResponse create(UserCreateForm request);
    UserDtoResponse update(Long id, UserUpdateForm request);
    void delete(Long id);
    User findUserById(Long id);
}
