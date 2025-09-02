package com.marlonreina.PersonalFitnessTracker.domain.repository;

import com.marlonreina.PersonalFitnessTracker.domain.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Optional<Workout> findByName(String name);

    @Query("SELECT DISTINCT w FROM Workout w " +
            "LEFT JOIN FETCH w.exercises e " +
            "LEFT JOIN FETCH e.exercise")
    List<Workout> findAllWithExercises();
}
