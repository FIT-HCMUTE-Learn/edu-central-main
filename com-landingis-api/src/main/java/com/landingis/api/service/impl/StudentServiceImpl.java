package com.landingis.api.service.impl;

import com.landingis.api.dto.student.StudentDto;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.mapper.StudentMapper;
import com.landingis.api.model.Student;
import com.landingis.api.repository.StudentRepository;
import com.landingis.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentDto getOneByUserId(Long id) {
        Student student = studentRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return studentMapper.toDto(student);
    }
}
