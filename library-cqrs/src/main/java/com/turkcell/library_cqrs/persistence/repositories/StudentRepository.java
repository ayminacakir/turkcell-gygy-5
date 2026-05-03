package com.turkcell.library_cqrs.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.library_cqrs.domain.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    boolean existsByEmail(String email);
}