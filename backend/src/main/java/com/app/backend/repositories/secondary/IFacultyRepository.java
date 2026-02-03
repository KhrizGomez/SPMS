package com.app.backend.repositories.secondary;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.backend.entities.secondary.Faculty;

public interface IFacultyRepository extends JpaRepository <Faculty, Integer> {}