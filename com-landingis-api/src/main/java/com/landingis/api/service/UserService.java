package com.landingis.api.service;

import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.request.user.UserCreateRequest;
import com.landingis.api.dto.request.user.UserUpdateRequest;
import com.landingis.api.dto.response.user.UserResponse;
import com.landingis.api.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    PaginationDto<UserResponse> getUsersPagination(String name, String username, int page, int size);
    UserResponse getOne(Long id);
    UserResponse create(UserCreateRequest request);
    UserResponse update(Long id, UserUpdateRequest request);
    void delete(Long id);
    User findUserById(Long id);
}
