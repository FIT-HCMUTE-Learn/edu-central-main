package com.landingis.api.model.criteria;

import com.landingis.api.model.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseCriteria {
    private Long userId;
    private String name;
    private String code;

    public Specification<Course> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> userCourseJoin = root.join("userCourses");
            Join<Object, Object> userJoin = userCourseJoin.join("user");

            if (userId != null) {
                predicates.add(cb.equal(userJoin.get("id"), userId));
            }

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (code != null && !code.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("code")), "%" + code.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
