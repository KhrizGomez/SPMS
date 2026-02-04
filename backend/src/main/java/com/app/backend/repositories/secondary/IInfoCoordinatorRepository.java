package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.InfoCoordinator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInfoCoordinatorRepository extends JpaRepository<InfoCoordinator, Integer> {}
