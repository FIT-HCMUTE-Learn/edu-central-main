package com.landingis.api.repository.criteria;

import com.landingis.api.entity.Course;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseCriteriaRepository extends BaseCriteriaRepository<Course> {

    public CourseCriteriaRepository() {
        super(Course.class);
    }

    public List<Course> findCourses(String name, String code, int page, int size) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("name", name);
        filters.put("code", code);

        return findEntities(filters, page, size, "id", true);
    }

    public long countCourses(String name, String code) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("name", name);
        filters.put("code", code);

        return countEntities(filters);
    }
}
