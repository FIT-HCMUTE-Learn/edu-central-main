package com.landingis.api.service.impl;

import com.landingis.api.dto.user.UserDto;
import com.landingis.api.entity.criteria.UserCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.form.user.UserCreateForm;
import com.landingis.api.form.user.UserUpdateForm;
import com.landingis.api.entity.User;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.mapper.UserMapper;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public PaginationDto<UserDto> getUsersPagination(UserCriteria userCriteria, Pageable pageable) {
        Specification<User> spec = userCriteria.getSpecification();
        Page<User> usersPage = userRepository.findAll(spec, pageable);

        return new PaginationDto<>(
                userMapper.toDtoList(usersPage.getContent()),
                usersPage.getTotalElements(),
                usersPage.getTotalPages()
        );
    }

    @Override
    public UserDto getOne(Long id) {
        User user = findUserById(id);

        return userMapper.toDto(user);
    }

    @Override
    public UserDto create(UserCreateForm request) {
        User user = userMapper.toEntity(request);

        if (userRepository.existsByUsername(user.getUsername())){
            throw new BusinessException("User with username " + user.getUsername() + " already exists");
        }

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(UserUpdateForm request) {
        Long id = request.getUserId();
        User user = findUserById(id);

        if (!Objects.equals(request.getHandle(), user.getUsername()) && userRepository.existsByUsername(request.getHandle())){
            throw new BusinessException("User with username " + user.getUsername() + " already exists");
        }

        userMapper.updateEntity(user, request);
        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        User user = findUserById(id);

        userRepository.delete(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
    }
}
