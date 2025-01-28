package com.landingis.api.service;

import com.landingis.api.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User update(Long id, User userUpdate);
    void delete(Long id);
    void registerCourse(Long userId, Long courseId);
}
