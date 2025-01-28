package com.landingis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.*;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Username cannot be null or empty")
    @Column(nullable = false, unique = true)
    String username;

    @NotNull(message = "Password cannot be null or empty")
    @Column(nullable = false)
    String password;

    @NotNull(message = "Full name cannot be null or empty")
    @Column(nullable = false)
    String fullName;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    @Column(nullable = false)
    Date birthDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}
