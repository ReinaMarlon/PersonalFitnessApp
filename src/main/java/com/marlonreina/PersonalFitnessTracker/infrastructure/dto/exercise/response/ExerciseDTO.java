package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;


}
