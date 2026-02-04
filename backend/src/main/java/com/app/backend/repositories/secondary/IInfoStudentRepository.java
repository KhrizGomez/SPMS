package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.InfoStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInfoStudentRepository extends JpaRepository<InfoStudent, Integer> {}
