package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class WorkoutLogDTO {
    private Long id;
    private Long workoutId;
    private String workoutName;
    private Long userId;
    private Integer totalTime;
    private Integer caloriesBurned;
    private LocalDateTime date;
    private List<WorkoutLogExerciseDTO> exercises;
}
