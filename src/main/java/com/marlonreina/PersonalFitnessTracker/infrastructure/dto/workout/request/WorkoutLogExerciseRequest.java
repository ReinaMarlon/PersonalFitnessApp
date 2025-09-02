package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WorkoutLogExerciseRequest {
    private long exerciseId;
    private Integer timeTaken;
}
