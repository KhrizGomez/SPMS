package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserPrimaryRepository extends JpaRepository<User, Integer> {
    Optional<User> findByCardId(String cardId);
    Optional<User> findByInstitutionalEmail(String institutionalEmail);
}
