package com.landingis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`course`")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Name cannot be null or empty")
    @Column(nullable = false)
    String name;

    @NotNull(message = "Code cannot be null or empty")
    @Column(nullable = false, unique = true)
    String code;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
