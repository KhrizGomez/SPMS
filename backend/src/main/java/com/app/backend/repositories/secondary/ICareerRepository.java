package com.app.backend.repositories.secondary;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.backend.entities.secondary.Career;

public interface ICareerRepository extends JpaRepository<Career, Integer> {}
