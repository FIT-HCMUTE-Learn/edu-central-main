package com.landingis.api.dto.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoResponse {
    private Long userId;
    private String handle;
    private String userFullName;
    private Date userBirthday;
}
