package com.marlonreina.PersonalFitnessTracker.application.usecase.workout;

import com.marlonreina.PersonalFitnessTracker.application.service.WorkoutLogService;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request.WorkoutLogRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog.WorkoutLogDTO;
import org.springframework.stereotype.Service;

@Service
public class WorkoutLogUseCase {
    private final WorkoutLogService workoutLogService;

    public WorkoutLogUseCase(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }

    public WorkoutLogDTO execute(WorkoutLogRequest request) {
        return workoutLogService.registerWorkoutLog(request);
    }
}
