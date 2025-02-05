package com.landingis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.landingis.api.composite.UserCourseId;
import com.landingis.api.enumeration.RegisterStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCourse {

    @EmbeddedId
    UserCourseId id = new UserCourseId();

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    @Column(nullable = false)
    LocalDate dateRegister;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RegisterStatus status;
}

