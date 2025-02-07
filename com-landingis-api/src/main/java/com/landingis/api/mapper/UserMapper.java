package com.landingis.api.mapper;

import com.landingis.api.dto.response.user.UserDtoResponse;
import com.landingis.api.form.user.UserCreateForm;
import com.landingis.api.form.user.UserUpdateForm;
import com.landingis.api.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "handle", target = "username"),
            @Mapping(source = "userPassword", target = "password"),
            @Mapping(source = "userFullName", target = "fullName"),
            @Mapping(source = "userBirthday", target = "birthDate"),
            @Mapping(source = "userGender", target = "gender")
    })
    User toEntity(UserCreateForm request);

    @Mappings({
            @Mapping(source = "handle", target = "username"),
            @Mapping(source = "userPassword", target = "password"),
            @Mapping(source = "userFullName", target = "fullName"),
            @Mapping(source = "userBirthday", target = "birthDate"),
            @Mapping(source = "userGender", target = "gender")
    })
    void updateEntity(@MappingTarget User user, UserUpdateForm request);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "handle"),
            @Mapping(source = "fullName", target = "userFullName"),
            @Mapping(source = "birthDate", target = "userBirthday"),
            @Mapping(source = "gender", target = "userGender")
    })
    @Named("mapUserToDto")
    UserDtoResponse toResponse(User user);

    @IterableMapping(elementTargetType = UserDtoResponse.class, qualifiedByName = "mapUserToDto")
    List<UserDtoResponse> toResponseList(List<User> users);
}
