package com.landingis.api.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    private Long id;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Boolean isSuperAdmin;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
