package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ExerciseRegisterRequest {
    private String name;
    private String description;
}