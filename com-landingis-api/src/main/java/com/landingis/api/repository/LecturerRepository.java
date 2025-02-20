package com.landingis.api.repository;

import com.landingis.api.model.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long>, JpaSpecificationExecutor<Lecturer> {
    Optional<Lecturer> findLecturerById(Long id);
}
