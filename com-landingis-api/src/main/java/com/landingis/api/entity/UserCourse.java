package com.landingis.api.entity;

import com.landingis.api.enumeration.CompletionStatus;
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

    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;
}
