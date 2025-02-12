package com.landingis.api.model.criteria;

import com.landingis.api.enumeration.LearningState;
import com.landingis.api.enumeration.RegisterStatus;
import com.landingis.api.model.UserCourse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserCourseCriteria {
    private Long userId;
    private Long courseId;
    private String username;
    private String fullName;
    private String courseName;
    private String courseCode;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private RegisterStatus registerStatus;
    private LearningState completionStatus;

    public Specification<UserCourse> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> userJoin = root.join("user");
            Join<Object, Object> courseJoin = root.join("course");

            if (userId != null) {
                predicates.add(cb.equal(userJoin.get("id"), userId));
            }
            if (courseId != null) {
                predicates.add(cb.equal(courseJoin.get("id"), courseId));
            }

            if (username != null && !username.isEmpty()) {
                predicates.add(cb.like(cb.lower(userJoin.get("username")), "%" + username.toLowerCase() + "%"));
            }
            if (fullName != null && !fullName.isEmpty()) {
                predicates.add(cb.like(cb.lower(userJoin.get("fullName")), "%" + fullName.toLowerCase() + "%"));
            }

            if (courseName != null && !courseName.isEmpty()) {
                predicates.add(cb.like(cb.lower(courseJoin.get("name")), "%" + courseName.toLowerCase() + "%"));
            }
            if (courseCode != null && !courseCode.isEmpty()) {
                predicates.add(cb.like(cb.lower(courseJoin.get("code")), "%" + courseCode.toLowerCase() + "%"));
            }

            if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dateRegister"), startDate));
            }
            if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dateRegister"), endDate));
            }

            if (registerStatus != null) {
                predicates.add(cb.equal(root.get("registerStatus"), registerStatus));
            }
            if (completionStatus != null) {
                predicates.add(cb.equal(root.get("completionStatus"), completionStatus));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
