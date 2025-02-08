package com.landingis.api.service.impl;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.entity.criteria.UserCourseCriteria;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.mapper.UserCourseMapper;
import com.landingis.api.repository.UserCourseRepository;
import com.landingis.api.service.CourseService;
import com.landingis.api.service.UserCourseService;
import com.landingis.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Override
    public UserCourseDto registerCourse(Long userId, Long courseId) {
        User user = userService.findUserById(userId);
        Course course = courseService.findCourseById(courseId);

        if (userCourseRepository.findByUserIdAndCourseId(userId, courseId).isPresent()) {
            throw new BusinessException("User is already registered for this course");
        }

        UserCourse userCourse = UserCourse.builder()
                .user(user)
                .course(course)
                .dateRegister(LocalDate.now())
                .status(RegisterStatus.PENDING)
                .build();

        UserCourse savedUserCourse = userCourseRepository.save(userCourse);

        return userCourseMapper.toResponse(savedUserCourse);
    }

    @Override
    public void unregisterCourse(Long userId, Long courseId) {
        UserCourse userCourse = findUserCourseById(userId, courseId);
        userCourseRepository.deleteById(userCourse.getId());
    }

    @Override
    public UserCourseDto getUserCourseResponse(Long userId, Long courseId) {
        UserCourse userCourse = findUserCourseById(userId, courseId);

        return userCourseMapper.toResponse(userCourse);
    }

    @Override
    public List<UserCourseDto> getUserCoursesByUserId(Long userId) {
        return userCourseMapper.toResponseList(userCourseRepository.findByUserId(userId));
    }

    @Override
    public List<UserCourseDto> getUserCoursesByCourseId(Long courseId) {
        return userCourseMapper.toResponseList(userCourseRepository.findByCourseId(courseId));
    }

    @Override
    public PaginationDto<UserCourseDto> getUserCoursesPagination(UserCourseCriteria userCourseCriteria, Pageable pageable) {
        Specification<UserCourse> spec = userCourseCriteria.getSpecification();
        Page<UserCourse> userCoursePage = userCourseRepository.findAll(spec, pageable);

        return new PaginationDto<>(
                userCourseMapper.toResponseList(userCoursePage.getContent()),
                userCoursePage.getTotalElements(),
                userCoursePage.getTotalPages()
        );
    }

    @Override
    public void updateStatus(Long userId, Long courseId, RegisterStatus status) {
        UserCourse userCourse = findUserCourseById(userId, courseId);

        userCourse.setStatus(status);

        userCourseRepository.save(userCourse);
    }

    @Override
    public UserCourse findUserCourseById(Long userId, Long courseId) {
        return userCourseRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course registration not found"));
    }
}
