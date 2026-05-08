package com.turkcell.library_cqrs.persistence.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.library_cqrs.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);// Login sırasında email'e göre kullanıcı bulmak için kullanacağız.

    boolean existsByEmail(String email);// Kayıt sırasında aynı email'e sahip bir kullanıcı olup olmadığını kontrol
                                        // etmek için kullanacağız.
}
