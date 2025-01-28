package com.landingis.api.service.impl;

import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.service.CourseService;
import com.landingis.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())){
            throw new BusinessException("User with username " + user.getUsername() + " already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User userUpdate) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );

        if (userRepository.existsByUsername(user.getUsername())){
            throw new BusinessException("User with username " + user.getUsername() + " already exists");
        }

        user.setUsername(userUpdate.getUsername());
        user.setPassword(userUpdate.getPassword());
        user.setBirthDate(userUpdate.getBirthDate());

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );

        userRepository.delete(user);
    }

    @Override
    public void registerCourse(Long userId, Long courseId) {
        User user = findById(userId);
        Course course = courseService.findById(courseId);

        user.getCourses().add(course);

        userRepository.save(user);
    }
}
