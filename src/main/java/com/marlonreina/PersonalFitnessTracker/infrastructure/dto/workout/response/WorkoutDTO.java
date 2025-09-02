package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.response;

import com.marlonreina.PersonalFitnessTracker.domain.model.WorkoutExercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class WorkoutDTO {
    private Long id;
    private String name;
    private String description;
    private String notes;
    private List<WorkoutExercise> exercises;

}
