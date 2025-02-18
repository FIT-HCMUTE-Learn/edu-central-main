package com.landingis.api.model.entity;

import com.landingis.api.model.audit.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Auditable {

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
