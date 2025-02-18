package com.landingis.api.repository;

import com.landingis.api.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findGroupByName(String name);
}
