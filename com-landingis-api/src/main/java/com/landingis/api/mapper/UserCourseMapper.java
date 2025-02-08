package com.landingis.api.mapper;

import com.landingis.api.dto.intermediary.UserCourseDto;
import com.landingis.api.entity.Course;
import com.landingis.api.entity.User;
import com.landingis.api.entity.UserCourse;
import com.landingis.api.enumeration.CompletionStatus;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.form.usercourse.UserCourseRegisterForm;
import com.landingis.api.form.usercourse.UserCourseUpdateForm;
import com.landingis.api.repository.CourseRepository;
import com.landingis.api.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class, CourseMapper.class})
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
            @Mapping(source = "dateRegister", target = "dateRegister", qualifiedByName = "formatStringToDate"),
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
        return (date != null) ? date.format(DateTimeFormatter.ISO_DATE) : null;
    }

    @Named("formatStringToDate")
    public static LocalDate formatStringToDate(String date) {
        return (date != null && !date.isEmpty()) ? LocalDate.parse(date, DateTimeFormatter.ISO_DATE) : null;
    }

    @Named("mapUserIdToUser")
    public User mapUserIdToUser(Long userId) {
        if (userId == null) return null;

        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + userId + " not found")
        );
    }

    @Named("mapCourseIdToCourse")
    public Course mapCourseIdToCourse(Long courseId) {
        if (courseId == null) return null;

        return courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course with id " + courseId + " not found")
        );
    }

    /**
     * Convert String to RegisterStatus Enum.
     */
    @Named("mapStringToRegisterStatus")
    public static RegisterStatus mapStringToRegisterStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("RegisterStatus cannot be null or empty");
        }
        try {
            return RegisterStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid RegisterStatus: '" + status + "'. Allowed values: "
                    + List.of(RegisterStatus.values()));
        }
    }

    @Named("mapStringToCompletionStatus")
    public static CompletionStatus mapStringToCompletionStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("CompletionStatus cannot be null or empty");
        }
        try {
            return CompletionStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid CompletionStatus: '" + status + "'. Allowed values: "
                    + List.of(CompletionStatus.values()));
        }
    }
}
