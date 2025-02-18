package com.landingis.api.repository;

import com.landingis.api.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query("SELECT s FROM Student s WHERE s.user.username = :username")
    Optional<Student> findByUsername(@Param("username") String username);
}
