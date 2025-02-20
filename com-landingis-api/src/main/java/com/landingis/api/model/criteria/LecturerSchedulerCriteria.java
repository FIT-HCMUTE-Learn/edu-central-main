package com.landingis.api.model.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerSchedulerCriteria {
    private Long lecturerId;
    private Long courseId;
    private Long periodId;
}
