package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {}
