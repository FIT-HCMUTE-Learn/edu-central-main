package com.landingis.api.repository;

import com.landingis.api.model.User;
import com.landingis.api.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    List<UserProjection> findAllProjectedBy();
    Optional<UserProjection> findUserById(Long id);
}
