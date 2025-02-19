package com.landingis.api.model.criteria;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PeriodCriteria {
    private String name;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Integer state;
}