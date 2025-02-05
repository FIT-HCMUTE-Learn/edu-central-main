package com.landingis.api.mapper;

import com.landingis.api.dto.response.user.UserResponse;
import com.landingis.api.dto.request.user.UserCreateRequest;
import com.landingis.api.dto.request.user.UserUpdateRequest;
import com.landingis.api.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "handle", target = "username"),
            @Mapping(source = "userPassword", target = "password"),
            @Mapping(source = "userFullName", target = "fullName"),
            @Mapping(source = "userBirthday", target = "birthDate")
    })
    User toEntity(UserCreateRequest request);

    @Mappings({
            @Mapping(source = "userPassword", target = "password"),
            @Mapping(source = "userFullName", target = "fullName"),
            @Mapping(source = "userBirthday", target = "birthDate")
    })
    void updateEntity(@MappingTarget User user, UserUpdateRequest request);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "handle"),
            @Mapping(source = "password", target = "userPassword"),
            @Mapping(source = "fullName", target = "userFullName"),
            @Mapping(source = "birthDate", target = "userBirthday")
    })
    UserResponse toResponse(User user);

    @IterableMapping(elementTargetType = UserResponse.class)
    List<UserResponse> toResponseList(List<User> users);
}
