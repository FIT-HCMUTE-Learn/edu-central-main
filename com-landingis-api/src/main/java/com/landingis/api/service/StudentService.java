package com.landingis.api.service;

import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.student.StudentDto;
import com.landingis.api.form.student.StudentCreateForm;
import com.landingis.api.model.criteria.StudentCriteria;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    StudentDto createStudent(StudentCreateForm form);
    StudentDto updateStudent(Long id, StudentCreateForm form);
    void deleteStudent(Long id);
    StudentDto getStudentById(Long id);
    PaginationDto<StudentDto> getStudentsPagination(StudentCriteria studentCriteria, Pageable pageable);
}
