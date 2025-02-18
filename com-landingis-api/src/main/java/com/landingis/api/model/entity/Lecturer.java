package com.landingis.api.model.entity;

import com.landingis.api.model.audit.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lecturers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer extends Auditable {

    @Id
    private Long id;

    @Column(nullable = false)
    private String workCode;

    @Column(nullable = false)
    private String career;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
