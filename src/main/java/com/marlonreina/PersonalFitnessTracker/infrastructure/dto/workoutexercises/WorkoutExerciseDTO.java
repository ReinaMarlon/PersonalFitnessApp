package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutexercises;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class WorkoutExerciseDTO {
    private Long id;
    private String exerciseName;
    private Integer sets;
    private Integer reps;


}
