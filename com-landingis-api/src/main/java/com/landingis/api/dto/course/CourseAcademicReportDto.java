package com.landingis.api.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseAcademicReportDto {
    private Long totalCourses;
    private Long totalStudents;
    private Double averageScore;
    private Long maleStudents;
    private Long femaleStudents;
}
