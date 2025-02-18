package com.landingis.api.mapper;

import com.landingis.api.dto.user.UserDto;
import com.landingis.api.form.user.UserCreateForm;
import com.landingis.api.form.user.UserUpdateForm;
import com.landingis.api.model.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "userHandle", target = "username"),
            @Mapping(source = "userPassword", target = "password"),
            @Mapping(source = "userFullName", target = "fullName"),
            @Mapping(source = "userGender", target = "gender")
    })
    User toEntity(UserCreateForm form);

    @Mappings({
            @Mapping(source = "userHandle", target = "username"),
            @Mapping(source = "userPassword", target = "password"),
            @Mapping(source = "userFullName", target = "fullName"),
            @Mapping(source = "userGender", target = "gender")
    })
    void updateEntity(@MappingTarget User user, UserUpdateForm form);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "userHandle"),
            @Mapping(source = "fullName", target = "userFullName"),
            @Mapping(source = "gender", target = "userGender")
    })
    @Named("mapUserToDto")
    UserDto toDto(User user);

    @IterableMapping(elementTargetType = UserDto.class, qualifiedByName = "mapUserToDto")
    List<UserDto> toDtoList(List<User> users);
}
