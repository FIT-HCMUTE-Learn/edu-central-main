package com.landingis.api.mapper;

import com.landingis.api.dto.admin.AdminDto;
import com.landingis.api.form.admin.AdminCreateForm;
import com.landingis.api.model.entity.Admin;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {

    @Mappings({
            @Mapping(source = "userHandle", target = "user.username"),
            @Mapping(source = "userPassword", target = "user.password"),
            @Mapping(source = "userFullName", target = "user.fullName"),
            @Mapping(source = "userGender", target = "user.gender"),
            @Mapping(source = "adminLevel", target = "level")
    })
    Admin toEntity(AdminCreateForm form);

    @Mappings({
            @Mapping(source = "userHandle", target = "user.username"),
            @Mapping(source = "userFullName", target = "user.fullName"),
            @Mapping(source = "userGender", target = "user.gender"),
            @Mapping(source = "adminLevel", target = "level")
    })
    void updateEntity(@MappingTarget Admin admin, AdminCreateForm form);

    @Mappings({
            @Mapping(source = "user.username", target = "user.userHandle"),
            @Mapping(source = "user.fullName", target = "user.userFullName"),
            @Mapping(source = "user.gender", target = "user.userGender"),
            @Mapping(source = "level", target = "adminLevel"),
            @Mapping(source = "isSuperAdmin", target = "isSuperAdmin")
    })
    AdminDto toDto(Admin admin);

    @IterableMapping(elementTargetType = AdminDto.class)
    List<AdminDto> toDtoList(List<Admin> admins);
}

