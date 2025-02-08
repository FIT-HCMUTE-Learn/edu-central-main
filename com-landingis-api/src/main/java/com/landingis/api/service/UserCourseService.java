package com.landingis.api.service;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.entity.criteria.UserCourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCourseService {
    UserCourseDto registerCourse(Long userId, Long courseId);
    void unregisterCourse(Long userId, Long courseId);
    UserCourseDto getUserCourseResponse(Long userId, Long courseId);
    List<UserCourseDto> getUserCoursesByUserId(Long userId);
    List<UserCourseDto> getUserCoursesByCourseId(Long courseId);
    PaginationDto<UserCourseDto> getUserCoursesPagination(UserCourseCriteria userCourseCriteria, Pageable pageable);
    void updateStatus(Long userId, Long courseId, RegisterStatus status);
    UserCourse findUserCourseById(Long userId, Long courseId);
}

