package com.landingis.api.service;

import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;

import java.util.List;
import java.util.Optional;

public interface UserCourseService {
    UserCourse registerCourse(Long userId, Long courseId);
    void unregisterCourse(Long userId, Long courseId);
    List<UserCourse> getUserCoursesByUserId(Long userId);
    List<UserCourse> getUserCoursesByCourseId(Long courseId);
    Optional<UserCourse> getUserCourse(Long userId, Long courseId);
    void updateStatus(Long userId, Long courseId, RegisterStatus status);
}

