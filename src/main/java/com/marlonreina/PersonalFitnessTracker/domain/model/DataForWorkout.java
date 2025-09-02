package com.marlonreina.PersonalFitnessTracker.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This class save the sets and reps from every exercise in the workout.

@Embeddable @Getter @Setter @NoArgsConstructor
public class DataForWorkout {
    @Column(name = "sets")
    private Integer set;
    private Integer reps;

    public DataForWorkout(Integer set, Integer reps) {
        this.set = set;
        this.reps = reps;
    }
}
