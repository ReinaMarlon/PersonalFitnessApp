package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExerciseEditRequest {
    private String name;
    private String description;
}
