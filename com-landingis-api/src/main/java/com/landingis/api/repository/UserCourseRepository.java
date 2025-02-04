package com.landingis.api.repository;

import com.landingis.api.composite.UserCourseId;
import com.landingis.api.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, UserCourseId> {
    List<UserCourse> findByUserId(Long userId);
    List<UserCourse> findByCourseId(Long courseId);
    Optional<UserCourse> findByUserIdAndCourseId(Long userId, Long courseId);
}
