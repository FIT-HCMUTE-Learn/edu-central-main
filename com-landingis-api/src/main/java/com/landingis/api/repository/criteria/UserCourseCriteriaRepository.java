package com.landingis.api.repository.criteria;

import com.landingis.api.entity.UserCourse;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserCourseCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserCourse> findUserCourses(Map<String, Object> filters, int page, int size, String sortBy, boolean ascending) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserCourse> criteriaQuery = criteriaBuilder.createQuery(UserCourse.class);
        Root<UserCourse> root = criteriaQuery.from(UserCourse.class);

        Join<Object, Object> userJoin = root.join("user", JoinType.INNER);
        Join<Object, Object> courseJoin = root.join("course", JoinType.INNER);

        List<Predicate> predicates = buildPredicates(filters, criteriaBuilder, root, userJoin, courseJoin);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (ascending) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
        }

        TypedQuery<UserCourse> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    public long countUserCourses(Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<UserCourse> root = countQuery.from(UserCourse.class);

        Join<Object, Object> userJoin = root.join("user", JoinType.INNER);
        Join<Object, Object> courseJoin = root.join("course", JoinType.INNER);

        List<Predicate> predicates = buildPredicates(filters, criteriaBuilder, root, userJoin, courseJoin);

        countQuery.select(criteriaBuilder.count(root));
        countQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private List<Predicate> buildPredicates(Map<String, Object> filters, CriteriaBuilder criteriaBuilder, Root<UserCourse> root,
                                            Join<Object, Object> userJoin, Join<Object, Object> courseJoin) {
        List<Predicate> predicates = new ArrayList<>();

        Map<String, Path<String>> fieldMappings = Map.of(
                "username", userJoin.get("username"),
                "fullName", userJoin.get("fullName"),
                "courseName", courseJoin.get("name"),
                "courseCode", courseJoin.get("code")
        );

        filters.forEach((key, value) ->
                Optional.ofNullable(value)
                        .map(Object::toString)
                        .filter(str -> !str.isEmpty())
                        .ifPresent(str -> predicates.add(criteriaBuilder.like(criteriaBuilder.lower(fieldMappings.get(key)), "%" + str.toLowerCase() + "%")))
        );

        return predicates;
    }
}
