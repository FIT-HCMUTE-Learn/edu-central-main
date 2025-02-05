package com.landingis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.landingis.api.enumeration.RegisterStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_course")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    @Column(nullable = false)
    LocalDate dateRegister;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RegisterStatus status;
}

