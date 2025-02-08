package com.landingis.api.mapper;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.entity.UserCourse;
import org.mapstruct.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class, CourseMapper.class})
public interface UserCourseMapper {

    @Mappings({
            @Mapping(source = "id", target = "userCourseId"),
            @Mapping(source = "user", target = "user", qualifiedByName = "mapUserToDto"),
            @Mapping(source = "course", target = "course", qualifiedByName = "mapCourseToDto"),
            @Mapping(source = "dateRegister", target = "dateRegister", qualifiedByName = "formatDate"),
            @Mapping(source = "status", target = "status")
    })
    @Named("mapUserCourseToDto")
    UserCourseDto toResponse(UserCourse userCourse);

    @IterableMapping(elementTargetType = UserCourseDto.class, qualifiedByName = "mapUserCourseToDto")
    List<UserCourseDto> toResponseList(List<UserCourse> userCourses);

    @Named("formatDate")
    static String formatDate(LocalDate date) {
        return (date != null) ? date.format(DateTimeFormatter.ISO_DATE) : null;
    }
}
