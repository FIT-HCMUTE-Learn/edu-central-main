package com.landingis.api.mapper;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.CompletionStatus;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.form.usercourse.UserCourseRegisterForm;
import com.landingis.api.form.usercourse.UserCourseUpdateForm;
import com.landingis.api.repository.CourseRepository;
import com.landingis.api.repository.UserRepository;
import com.landingis.api.util.FormatUtils;
import com.landingis.api.util.MappingUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, CourseMapper.class})
@Component
public abstract class UserCourseMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Mappings({
            @Mapping(source = "userId", target = "user", qualifiedByName = "mapUserIdToUser"),
            @Mapping(source = "courseId", target = "course", qualifiedByName = "mapCourseIdToCourse")
    })
    public abstract UserCourse toEntity(UserCourseRegisterForm form);

    @Mappings({
            @Mapping(source = "userId", target = "user", qualifiedByName = "mapUserIdToUser"),
            @Mapping(source = "courseId", target = "course", qualifiedByName = "mapCourseIdToCourse"),
            @Mapping(source = "dateRegister", target = "dateRegister", qualifiedByName = "parseDate"),
            @Mapping(source = "registerStatus", target = "registerStatus", qualifiedByName = "mapStringToRegisterStatus"),
            @Mapping(source = "completionStatus", target = "completionStatus", qualifiedByName = "mapStringToCompletionStatus")
    })
    public abstract void updateEntity(@MappingTarget UserCourse userCourse, UserCourseUpdateForm request);

    @Mappings({
            @Mapping(source = "id", target = "userCourseId"),
            @Mapping(source = "user", target = "user", qualifiedByName = "mapUserToDto"),
            @Mapping(source = "course", target = "course", qualifiedByName = "mapCourseToDto"),
            @Mapping(source = "dateRegister", target = "dateRegister", qualifiedByName = "formatDate"),
            @Mapping(source = "registerStatus", target = "registerStatus"),
            @Mapping(source = "completionStatus", target = "completionStatus")
    })
    @Named("mapUserCourseToDto")
    public abstract UserCourseDto toDto(UserCourse userCourse);

    @IterableMapping(elementTargetType = UserCourseDto.class, qualifiedByName = "mapUserCourseToDto")
    public abstract List<UserCourseDto> toDtoList(List<UserCourse> userCourses);

    @Named("formatDate")
    public static String formatDate(LocalDate date) {
        return FormatUtils.formatDate(date);
    }

    @Named("parseDate")
    public static LocalDate parseDate(String date) {
        return FormatUtils.parseDate(date);
    }

    @Named("mapUserIdToUser")
    public User mapUserIdToUser(Long userId) {
        return MappingUtils.getEntityById(userRepository, userId, User.class);
    }

    @Named("mapCourseIdToCourse")
    public Course mapCourseIdToCourse(Long courseId) {
        return MappingUtils.getEntityById(courseRepository, courseId, Course.class);
    }

    @Named("mapStringToRegisterStatus")
    public static RegisterStatus mapStringToRegisterStatus(String value) {
        return MappingUtils.mapStringToEnum(value, RegisterStatus.class);
    }

    @Named("mapStringToCompletionStatus")
    public static CompletionStatus mapStringToCompletionStatus(String value) {
        return MappingUtils.mapStringToEnum(value, CompletionStatus.class);
    }

}
