package com.landingis.api.dto.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.landingis.api.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    private UserDto user;
    private String studentCode;
    private Date birthDate;
}
