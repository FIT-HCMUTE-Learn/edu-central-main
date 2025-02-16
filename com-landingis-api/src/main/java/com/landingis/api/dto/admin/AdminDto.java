package com.landingis.api.dto.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.landingis.api.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminDto {
    private UserDto user;
    private Integer adminLevel;
    private Boolean isSuperAdmin;
}
