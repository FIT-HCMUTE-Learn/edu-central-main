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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer level;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
