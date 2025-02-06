package com.landingis.api.service;

import com.landingis.api.criteria.UserCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.request.user.UserCreateRequest;
import com.landingis.api.dto.request.user.UserUpdateRequest;
import com.landingis.api.dto.response.user.UserResponse;
import com.landingis.api.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    PaginationDto<UserResponse> getUsersPagination(UserCriteria userCriteria, Pageable pageable);
    UserResponse getOne(Long id);
    UserResponse create(UserCreateRequest request);
    UserResponse update(Long id, UserUpdateRequest request);
    void delete(Long id);
    User findUserById(Long id);
}
