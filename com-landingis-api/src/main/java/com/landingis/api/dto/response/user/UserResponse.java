package com.landingis.api.dto.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserResponse {
    Long userId;
    String handle;
    String userPassword;
    String userFullName;
    Date userBirthday;
}
