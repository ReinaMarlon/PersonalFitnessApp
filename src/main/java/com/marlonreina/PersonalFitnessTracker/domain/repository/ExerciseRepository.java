package com.marlonreina.PersonalFitnessTracker.domain.repository;

import com.marlonreina.PersonalFitnessTracker.domain.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByName(String name);

    boolean existsByName(String name);

}
