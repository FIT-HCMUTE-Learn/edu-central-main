package com.landingis.api.service;

import com.landingis.api.entity.criteria.UserCourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.response.intermediary.UserCourseDtoResponse;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCourseService {
    UserCourseDtoResponse registerCourse(Long userId, Long courseId);
    void unregisterCourse(Long userId, Long courseId);
    UserCourseDtoResponse getUserCourseResponse(Long userId, Long courseId);
    List<UserCourseDtoResponse> getUserCoursesByUserId(Long userId);
    List<UserCourseDtoResponse> getUserCoursesByCourseId(Long courseId);
    PaginationDto<UserCourseDtoResponse> getUserCoursesPagination(UserCourseCriteria userCourseCriteria, Pageable pageable);
    void updateStatus(Long userId, Long courseId, RegisterStatus status);
    UserCourse findUserCourseById(Long userId, Long courseId);
}

