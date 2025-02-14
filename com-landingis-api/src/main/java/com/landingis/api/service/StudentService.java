package com.landingis.api.service;

import com.landingis.api.dto.student.StudentDto;

public interface StudentService {
    StudentDto getOneByUserId(Long id);
}
