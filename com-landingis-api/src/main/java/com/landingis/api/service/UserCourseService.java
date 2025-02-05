package com.landingis.api.service;

import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.response.intermediary.UserCourseResponse;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;

import java.util.List;

public interface UserCourseService {
    UserCourseResponse registerCourse(Long userId, Long courseId);
    void unregisterCourse(Long userId, Long courseId);
    UserCourseResponse getUserCourseResponse(Long userId, Long courseId);
    List<UserCourseResponse> getUserCoursesByUserId(Long userId);
    List<UserCourseResponse> getUserCoursesByCourseId(Long courseId);
    PaginationDto<UserCourseResponse> getUserCoursesPagination(String username, String fullName, String courseName, String courseCode, int page, int size);
    void updateStatus(Long userId, Long courseId, RegisterStatus status);
    UserCourse findUserCourseById(Long userId, Long courseId);
}

