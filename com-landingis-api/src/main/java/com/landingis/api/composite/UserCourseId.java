package com.landingis.api.composite;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCourseId implements Serializable {
    Long userId;
    Long courseId;
}

