package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutLogExerciseDTO {
    private Long exerciseId;
    private String exerciseName;
    private Integer timeTaken;
}