package com.marlonreina.PersonalFitnessTracker.domain.repository;

import com.marlonreina.PersonalFitnessTracker.domain.model.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkoutLogRepository extends JpaRepository<WorkoutLog, Long> {

}
