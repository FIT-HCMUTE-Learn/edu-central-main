package com.landingis.api.service.impl;

import com.landingis.api.composite.UserCourseId;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.repository.UserCourseRepository;
import com.landingis.api.service.CourseService;
import com.landingis.api.service.UserCourseService;
import com.landingis.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Override
    public UserCourse registerCourse(Long userId, Long courseId) {
        User user = userService.findUserById(userId);
        Course course = courseService.findCourseById(courseId);

        UserCourseId userCourseId = new UserCourseId(userId, courseId);
        if (userCourseRepository.existsById(userCourseId)) {
            throw new BusinessException("User is already registered for this course");
        }

        UserCourse userCourse = new UserCourse();
        userCourse.setId(userCourseId);
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setDateRegister(LocalDate.now());
        userCourse.setStatus(RegisterStatus.PENDING);

        return userCourseRepository.save(userCourse);
    }

    @Override
    public void unregisterCourse(Long userId, Long courseId) {
        UserCourseId userCourseId = new UserCourseId(userId, courseId);

        if (!userCourseRepository.existsById(userCourseId)) {
            throw new ResourceNotFoundException("Course registration not found");
        }

        userCourseRepository.deleteById(userCourseId);
    }

    @Override
    public Optional<UserCourse> getUserCourse(Long userId, Long courseId) {
        UserCourseId userCourseId = new UserCourseId(userId, courseId);

        return userCourseRepository.findById(userCourseId);
    }

    @Override
    public List<UserCourse> getUserCoursesByUserId(Long userId) {
        return userCourseRepository.findByUserId(userId);
    }

    @Override
    public List<UserCourse> getUserCoursesByCourseId(Long courseId) {
        return userCourseRepository.findByCourseId(courseId);
    }

    @Override
    public void updateStatus(Long userId, Long courseId, RegisterStatus status) {
        UserCourse userCourse = getUserCourse(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course registration not found"));

        userCourse.setStatus(status);

        userCourseRepository.save(userCourse);
    }
}
