package com.landingis.api.projection;

public interface CourseAcademicReportProjection {
    Integer getTotalCourses();
    Integer getTotalStudents();
    Double getAverageScore();
    Integer getMaleStudents();
    Integer getFemaleStudents();
}
