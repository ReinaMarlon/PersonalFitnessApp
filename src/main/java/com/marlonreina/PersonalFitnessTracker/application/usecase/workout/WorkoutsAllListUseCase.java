package com.marlonreina.PersonalFitnessTracker.application.usecase.workout;

import com.marlonreina.PersonalFitnessTracker.application.service.WorkoutService;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.response.WorkoutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutsAllListUseCase {
    private final WorkoutService workoutService;

    public WorkoutsAllListUseCase(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    public List<WorkoutDTO> execute() { return workoutService.getAllWorkouts(); }


}
