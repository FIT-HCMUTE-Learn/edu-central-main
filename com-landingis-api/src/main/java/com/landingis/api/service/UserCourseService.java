package com.landingis.api.service;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.model.criteria.UserCourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.model.entity.UserCourse;
import com.landingis.api.form.usercourse.UserCourseRegisterForm;
import com.landingis.api.form.usercourse.UserCourseUnregisterForm;
import com.landingis.api.form.usercourse.UserCourseUpdateForm;
import org.springframework.data.domain.Pageable;

public interface UserCourseService {
    UserCourseDto registerCourse(UserCourseRegisterForm form);
    void unregisterCourse(UserCourseUnregisterForm form);
    PaginationDto<UserCourseDto> getUserCoursesPagination(UserCourseCriteria userCourseCriteria, Pageable pageable);
    UserCourseDto update(UserCourseUpdateForm form);
    UserCourse findUserCourseById(Long userId, Long courseId);
    void checkAndUpdateCourseCompletion();
}
