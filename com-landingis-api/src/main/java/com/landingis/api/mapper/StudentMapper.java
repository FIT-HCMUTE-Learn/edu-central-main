package com.landingis.api.mapper;

import com.landingis.api.dto.student.StudentDto;
import com.landingis.api.form.student.StudentCreateForm;
import com.landingis.api.model.entity.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    @Mappings({
            @Mapping(source = "userHandle", target = "user.username"),
            @Mapping(source = "userPassword", target = "user.password"),
            @Mapping(source = "userFullName", target = "user.fullName"),
            @Mapping(source = "userGender", target = "user.gender"),
            @Mapping(source = "studentCode", target = "studentCode"),
            @Mapping(source = "studentBirthday", target = "birthDate")
    })
    Student toEntity(StudentCreateForm form);

    @Mappings({
            @Mapping(source = "userHandle", target = "user.username"),
            @Mapping(source = "userFullName", target = "user.fullName"),
            @Mapping(source = "userGender", target = "user.gender"),
            @Mapping(source = "studentCode", target = "studentCode"),
            @Mapping(source = "studentBirthday", target = "birthDate")
    })
    void updateEntity(@MappingTarget Student student, StudentCreateForm form);

    @Mappings({
            @Mapping(source = "user.username", target = "user.userHandle"),
            @Mapping(source = "user.fullName", target = "user.userFullName"),
            @Mapping(source = "user.gender", target = "user.userGender"),
            @Mapping(source = "studentCode", target = "studentCode"),
            @Mapping(source = "birthDate", target = "studentBirthday")
    })
    StudentDto toDto(Student student);

    @IterableMapping(elementTargetType = StudentDto.class)
    List<StudentDto> toDtoList(List<Student> students);
}
