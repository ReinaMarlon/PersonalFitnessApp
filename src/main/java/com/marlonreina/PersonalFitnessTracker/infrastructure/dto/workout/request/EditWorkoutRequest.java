package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request;

import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutexercises.WorkoutExerciseDTO;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog.WorkoutLogDTO;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter @Setter
public class EditWorkoutRequest {
    private String name;
    private String description;
    private String notes;
    private List<WorkoutLogDTO> logs;
    private List<WorkoutExerciseDTO> exercises;
}
