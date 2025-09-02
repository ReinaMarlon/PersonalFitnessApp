package com.marlonreina.PersonalFitnessTracker.application.usecase.exercise;

import com.marlonreina.PersonalFitnessTracker.application.service.ExerciseService;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.response.ExerciseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseListAllUseCase {
    private final ExerciseService exerciseService;

    public ExerciseListAllUseCase(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    public List<ExerciseDTO> execute () {
        return exerciseService.getAllExercises();
    }
}
