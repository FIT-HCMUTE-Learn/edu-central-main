package com.landingis.api.service.impl;

import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.student.StudentDto;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.form.student.StudentCreateForm;
import com.landingis.api.mapper.StudentMapper;
import com.landingis.api.model.Group;
import com.landingis.api.model.Student;
import com.landingis.api.model.User;
import com.landingis.api.model.criteria.StudentCriteria;
import com.landingis.api.repository.GroupRepository;
import com.landingis.api.repository.StudentRepository;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public StudentDto createStudent(StudentCreateForm form) {
        if (studentRepository.findByUsername(form.getUserHandle()).isPresent()) {
            throw new BusinessException("User with username " + form.getUserHandle() + " already exists");
        }

        User user = new User();
        user.setUsername(form.getUserHandle());
        user.setPassword(passwordEncoder.encode(form.getUserPassword()));
        user.setFullName(form.getUserFullName());
        user.setGender(form.getUserGender());

        Group group = groupRepository.findGroupByName("GROUP_USER");
        user.setGroup(group);

        User savedUser = userRepository.save(user);

        Student student = new Student();
        student.setStudentCode(form.getStudentCode());
        student.setBirthDate(form.getStudentBirthday());
        student.setUser(savedUser);

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    @Transactional
    public StudentDto updateStudent(Long id, StudentCreateForm form) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (!form.getUserHandle().equals(student.getUser().getUsername())
        && studentRepository.findByUsername(form.getUserHandle()).isPresent()) {
            throw new BusinessException("User with username " + form.getUserHandle() + " already exists");
        }

        User user = student.getUser();
        user.setUsername(form.getUserHandle());
        user.setPassword(passwordEncoder.encode(form.getUserPassword()));
        user.setFullName(form.getUserFullName());
        user.setGender(form.getUserGender());
        userRepository.save(user);

        student.setStudentCode(form.getStudentCode());
        student.setBirthDate(form.getStudentBirthday());
        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toDto(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return studentMapper.toDto(student);
    }

    @Override
    public PaginationDto<StudentDto> getStudentsPagination(StudentCriteria studentCriteria, Pageable pageable) {
        Specification<Student> spec = studentCriteria.getSpecification();
        Page<Student> studentsPage = studentRepository.findAll(spec, pageable);

        return new PaginationDto<>(
                studentMapper.toDtoList(studentsPage.getContent()),
                studentsPage.getTotalElements(),
                studentsPage.getTotalPages()
        );
    }
}
