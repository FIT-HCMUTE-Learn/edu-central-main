package com.landingis.api.model;

import com.landingis.api.enumeration.LearningState;
import com.landingis.api.enumeration.RegisterStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_course")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private LocalDate dateRegister;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegisterStatus registerStatus;

    @Column(nullable = false)
    private Double score;

    @Enumerated(EnumType.STRING)
    private LearningState learningState;
}
