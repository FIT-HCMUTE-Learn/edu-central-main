package com.landingis.api.composite;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserCourseId implements Serializable {

    private Long userId;
    private Long courseId;

    public UserCourseId() {}

    public UserCourseId(Long userId, Long courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }
}

