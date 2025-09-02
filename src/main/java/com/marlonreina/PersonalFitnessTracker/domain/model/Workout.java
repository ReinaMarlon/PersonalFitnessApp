package com.marlonreina.PersonalFitnessTracker.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//This class is to basic data from workouts.

@Entity @Getter @Setter @NoArgsConstructor
public class Workout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String notes;

    @OneToMany(mappedBy = "workoutReference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutLog> logs = new ArrayList<>();


    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkoutExercise> exercises = new ArrayList<>();

    public Workout(Long id, String name, String description, String notes, List<WorkoutLog> logs, List<WorkoutExercise> exercises) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.logs = logs;
        this.exercises = exercises;
    }
}
