package com.marlonreina.PersonalFitnessTracker.application.service;

import com.marlonreina.PersonalFitnessTracker.domain.model.Workout;
import com.marlonreina.PersonalFitnessTracker.domain.repository.WorkoutRepository;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.response.WorkoutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// If a service does not inject a Repository or another Service,
// it is considered a utility service (contains business logic without database access).
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    //Dependency Injection
    public WorkoutService(WorkoutRepository workoutRepository) { this.workoutRepository = workoutRepository; }

    public List<WorkoutDTO> getAllWorkouts(){
        List<Workout> workouts = workoutRepository.findAllWithExercises();

        return workouts.stream()
                .map(workout -> new WorkoutDTO(
                        workout.getId(),
                        workout.getName(),
                        workout.getDescription(),
                        workout.getNotes(),
                        workout.getExercises()
                ))
                .toList();
    }


}
