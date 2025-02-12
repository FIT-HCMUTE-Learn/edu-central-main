package com.landingis.api.repository;

import com.landingis.api.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long>, JpaSpecificationExecutor<UserCourse> {
    List<UserCourse> findByUserId(Long userId);

    List<UserCourse> findByCourseId(Long courseId);

    Optional<UserCourse> findByUserIdAndCourseId(Long userId, Long courseId);
}
