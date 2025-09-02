package com.marlonreina.PersonalFitnessTracker.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

//This class save the relation between User and Workout exercise whith his data Eg
// (User 1 have this workout with three exercises and these exercises hava  a: first, 3 sets and 15 reps... etc.)

@Entity @Getter @Setter @NoArgsConstructor
public class WorkoutLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //WorkoutLog Main Data
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userReference;
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workoutReference;

    private Integer totalTime;
    private LocalDateTime date = LocalDateTime.now();

    @OneToMany(mappedBy = "workoutLog", cascade = CascadeType.ALL)
    private List<WorkoutLogExercise> exercises;

    private Integer caloriesBurned;

    public WorkoutLog(Long id, User userReference, Workout workoutReference, Integer totalTime, LocalDateTime date, List<WorkoutLogExercise> exercises, Integer caloriesBurned) {
        this.id = id;
        this.userReference = userReference;
        this.workoutReference = workoutReference;
        this.totalTime = totalTime;
        this.date = date;
        this.exercises = exercises;
        this.caloriesBurned = caloriesBurned;
    }

}
