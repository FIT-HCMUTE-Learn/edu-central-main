package com.landingis.api.repository;

import com.landingis.api.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

    Optional<Admin> findByUserId(Long id);

    @Query("SELECT a FROM Admin a WHERE a.user.username = :username")
    Optional<Admin> findByUsername(@Param("username") String username);
}
