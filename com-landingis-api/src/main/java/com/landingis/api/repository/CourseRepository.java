package com.landingis.api.repository;

import com.landingis.api.dto.course.CourseAcademicReportDto;
import com.landingis.api.model.entity.Course;
import com.landingis.api.projection.CourseAcademicReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    boolean existsByCode(String code);
    Optional<Course> findCourseById(Long id);

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

    @Query("SELECT new com.landingis.api.dto.course.CourseAcademicReportDto(" +
            "(SELECT COUNT(c) FROM Course c), " +
            "(SELECT COUNT(DISTINCT uc.user.id) FROM UserCourse uc), " +
            "(SELECT COALESCE(AVG(uc.score), 0) FROM UserCourse uc WHERE uc.score IS NOT NULL), " +
            "(SELECT COUNT(DISTINCT uc.user.id) FROM UserCourse uc WHERE uc.user.gender = 1), " +
            "(SELECT COUNT(DISTINCT uc.user.id) FROM UserCourse uc WHERE uc.user.gender = 2)) " +
            "FROM UserCourse uc WHERE uc.id = (SELECT MIN(uc2.id) FROM UserCourse uc2)")
    CourseAcademicReportDto getAcademicReport();

    @Query("SELECT " +
            "(SELECT COUNT(c) FROM Course c) AS totalCourses, " +
            "(SELECT COUNT(DISTINCT uc.user.id) FROM UserCourse uc) AS totalStudents, " +
            "(SELECT COALESCE(AVG(uc.score), 0) FROM UserCourse uc WHERE uc.score IS NOT NULL) AS averageScore, " +
            "(SELECT COUNT(DISTINCT uc.user.id) FROM UserCourse uc WHERE uc.user.gender = 1) AS maleStudents, " +
            "(SELECT COUNT(DISTINCT uc.user.id) FROM UserCourse uc WHERE uc.user.gender = 2) AS femaleStudents " +
            "FROM UserCourse uc WHERE uc.id = (SELECT MIN(uc2.id) FROM UserCourse uc2)")
    CourseAcademicReportProjection getAcademicReportProjection();
}
