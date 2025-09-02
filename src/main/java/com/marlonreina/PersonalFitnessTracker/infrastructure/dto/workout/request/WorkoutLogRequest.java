package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class WorkoutLogRequest {
    private Long workoutId;
    private Long userId;
    private List<WorkoutLogExerciseRequest> exercises;
    private Integer totalTime;
    private Integer caloriesBurned;
}