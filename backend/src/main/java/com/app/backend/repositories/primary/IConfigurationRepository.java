package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConfigurationRepository extends JpaRepository<Configuration, Integer> {
}
