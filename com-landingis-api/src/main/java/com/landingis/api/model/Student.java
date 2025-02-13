package com.landingis.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentCode;

    @Column(nullable = false)
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
