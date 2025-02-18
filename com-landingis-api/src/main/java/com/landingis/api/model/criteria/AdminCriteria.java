package com.landingis.api.model.criteria;

import com.landingis.api.model.entity.Admin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AdminCriteria {
    private Integer level;
    private Boolean isSuperAdmin;
    private String userHandle;
    private String userFullName;
    private Integer userGender;

    public Specification<Admin> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Join với User
            Join<Object, Object> userJoin = root.join("user");

            if (level != null) {
                predicates.add(cb.equal(root.get("level"), level));
            }

            if (isSuperAdmin != null) {
                predicates.add(cb.equal(root.get("isSuperAdmin"), isSuperAdmin));
            }

            if (userHandle != null && !userHandle.isEmpty()) {
                predicates.add(cb.like(cb.lower(userJoin.get("username")), "%" + userHandle.toLowerCase() + "%"));
            }

            if (userFullName != null && !userFullName.isEmpty()) {
                predicates.add(cb.like(cb.lower(userJoin.get("fullName")), "%" + userFullName.toLowerCase() + "%"));
            }

            if (userGender != null) {
                predicates.add(cb.equal(userJoin.get("gender"), userGender));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
