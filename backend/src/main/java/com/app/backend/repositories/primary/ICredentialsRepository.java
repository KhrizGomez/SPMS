package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICredentialsRepository extends JpaRepository<Credentials, Integer> {
    Optional<Credentials> findByUser_IdUser(Integer idUser);
}
