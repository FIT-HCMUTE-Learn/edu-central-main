package com.landingis.api.repository;

import com.landingis.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    boolean existsByCode(String code);

    @Modifying
    @Query("UPDATE Course c SET c.status = 'COMPLETED' " +
            "WHERE c.status = 'IN_PROGRESS' " +
            "AND c.id IN (" +
            "   SELECT uc.course.id FROM UserCourse uc " +
            "   GROUP BY uc.course.id " +
            "   HAVING COUNT(CASE WHEN uc.learningState IS NOT NULL " +
            "                   AND uc.learningState != 'COMPLETED' THEN 1 END) = 0" +
            ")")
    void updateCompletedCourses();
}
