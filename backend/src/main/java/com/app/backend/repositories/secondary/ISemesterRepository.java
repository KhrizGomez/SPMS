package com.app.backend.repositories.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.backend.entities.secondary.Semester;

public interface ISemesterRepository extends JpaRepository<Semester, Integer> {}
