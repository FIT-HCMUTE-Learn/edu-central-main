package com.landingis.api.model.entity;

import com.landingis.api.model.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Permission extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String pcode;

    @ManyToMany(mappedBy = "permissions")
    private List<Group> groups;
}
