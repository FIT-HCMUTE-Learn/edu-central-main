package com.landingis.api.entity;

import com.landingis.api.composite.UserCourseId;
import com.landingis.api.enumeration.RegisterStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourse {

    @EmbeddedId
    private UserCourseId id = new UserCourseId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private LocalDate dateRegister;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegisterStatus status;
}

