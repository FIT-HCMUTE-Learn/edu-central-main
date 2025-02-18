package com.landingis.api.model.criteria;

import com.landingis.api.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserCriteria {
    private Long courseId;
    private String username;
    private String fullName;

    public Specification<User> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> userCourseJoin = root.join("userCourses");
            Join<Object, Object> courseJoin = userCourseJoin.join("user");

            if (courseId != null) {
                predicates.add(cb.equal(courseJoin.get("id"), courseId));
            }

            if (username != null && !username.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("username")), "%" + username.toLowerCase() + "%"));
            }

            if (fullName != null && !fullName.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
