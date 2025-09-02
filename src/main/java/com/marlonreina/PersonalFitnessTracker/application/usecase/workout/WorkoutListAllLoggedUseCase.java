package com.marlonreina.PersonalFitnessTracker.application.usecase.workout;


import com.marlonreina.PersonalFitnessTracker.application.service.WorkoutLogService;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request.WorkoutListAllLoggedRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog.WorkoutLogDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutListAllLoggedUseCase {
    private WorkoutLogService workoutLogService;

    public WorkoutListAllLoggedUseCase(WorkoutLogService workoutLogService) { this.workoutLogService = workoutLogService; }

    public List<WorkoutLogDTO> execute(WorkoutListAllLoggedRequest request) {
        return workoutLogService.getAllLoggedWorkoutsByUser(request.getUserId());
    }
}
