package com.landingis.api.repository.criteria;

import com.landingis.api.entity.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserCriteriaRepository extends BaseCriteriaRepository<User> {

    public UserCriteriaRepository() {
        super(User.class);
    }

    public List<User> findUsers(String name, String username, int page, int size) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("fullName", name);
        filters.put("username", username);

        return findEntities(filters, page, size, "id", true);
    }

    public long countUsers(String name, String username) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("fullName", name);
        filters.put("username", username);

        return countEntities(filters);
    }
}
