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
    private Long id;

    @Column(nullable = false)
    private String studentCode;

    @Column(nullable = false)
    private Date birthDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
