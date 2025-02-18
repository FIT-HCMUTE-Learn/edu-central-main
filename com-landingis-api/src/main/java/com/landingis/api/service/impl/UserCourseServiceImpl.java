package com.landingis.api.service.impl;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.model.criteria.UserCourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.model.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.form.usercourse.UserCourseRegisterForm;
import com.landingis.api.form.usercourse.UserCourseUnregisterForm;
import com.landingis.api.form.usercourse.UserCourseUpdateForm;
import com.landingis.api.mapper.UserCourseMapper;
import com.landingis.api.repository.CourseRepository;
import com.landingis.api.repository.UserCourseRepository;
import com.landingis.api.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Override
    public UserCourseDto registerCourse(UserCourseRegisterForm form) {
        if (userCourseRepository.findByUserIdAndCourseId(form.getUserId(), form.getCourseId()).isPresent()) {
            throw new BusinessException("User is already registered for this course");
        }

        UserCourse userCourse = userCourseMapper.toEntity(form);
        userCourse.setDateRegister(LocalDate.now());
        userCourse.setRegisterStatus(RegisterStatus.PENDING);

        UserCourse savedUserCourse = userCourseRepository.save(userCourse);

        return userCourseMapper.toDto(savedUserCourse);
    }

    @Override
    public void unregisterCourse(UserCourseUnregisterForm form) {
        UserCourse userCourse = findUserCourseById(form.getUserId(), form.getCourseId());
        userCourseRepository.deleteById(userCourse.getId());
    }

    @Override
    public PaginationDto<UserCourseDto> getUserCoursesPagination(UserCourseCriteria userCourseCriteria, Pageable pageable) {
        Specification<UserCourse> spec = userCourseCriteria.getSpecification();
        Page<UserCourse> userCoursePage = userCourseRepository.findAll(spec, pageable);

        return new PaginationDto<>(
                userCourseMapper.toDtoList(userCoursePage.getContent()),
                userCoursePage.getTotalElements(),
                userCoursePage.getTotalPages()
        );
    }

    @Override
    public UserCourseDto update(UserCourseUpdateForm form) {
        UserCourse userCourse = findUserCourseById(form.getUserId(), form.getCourseId());

        userCourseMapper.updateEntity(userCourse, form);
        UserCourse updatedUserCourse = userCourseRepository.save(userCourse);

        return userCourseMapper.toDto(updatedUserCourse);
    }

    @Override
    public UserCourse findUserCourseById(Long userId, Long courseId) {
        return userCourseRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course registration not found"));
    }

    @Override
    @Transactional
    public void checkAndUpdateCourseCompletion() {
        courseRepository.updateCompletedCourses();
    }
}
