package com.landingis.api.model.entity;

import com.landingis.api.model.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer kind;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_permissions",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;
}
