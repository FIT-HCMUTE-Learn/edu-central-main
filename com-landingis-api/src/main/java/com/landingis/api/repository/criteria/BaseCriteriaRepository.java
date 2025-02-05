package com.landingis.api.repository.criteria;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseCriteriaRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public BaseCriteriaRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> findEntities(Map<String, Object> filters, int page, int size, String sortBy, boolean ascending) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = buildPredicates(filters, criteriaBuilder, root);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (ascending) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
        }

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    public long countEntities(Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = countQuery.from(entityClass);

        List<Predicate> predicates = buildPredicates(filters, criteriaBuilder, root);

        countQuery.select(criteriaBuilder.count(root));
        countQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private List<Predicate> buildPredicates(Map<String, Object> filters, CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Predicate> predicates = new ArrayList<>();

        filters.forEach((field, value) -> {
            if (value instanceof String && !((String) value).isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), "%" + value.toString().toLowerCase() + "%"));
            }
        });

        return predicates;
    }
}
