package com.landingis.api.mapper;

import com.landingis.api.dto.student.StudentDto;
import com.landingis.api.model.Student;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface StudentMapper {

    @Mappings({
            @Mapping(source = "user", target = "user", qualifiedByName = "mapUserToDto"),
            @Mapping(source = "studentCode", target = "studentCode"),
            @Mapping(source = "birthDate", target = "birthDate")
    })
    @Named("mapStudentToDto")
    StudentDto toDto(Student student);

    @IterableMapping(elementTargetType = StudentDto.class, qualifiedByName = "mapStudentToDto")
    List<StudentDto> toDtoList(List<Student> students);
}
