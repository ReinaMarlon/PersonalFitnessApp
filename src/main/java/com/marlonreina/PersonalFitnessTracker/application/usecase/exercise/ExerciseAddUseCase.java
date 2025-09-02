package com.marlonreina.PersonalFitnessTracker.application.usecase.exercise;

import com.marlonreina.PersonalFitnessTracker.application.service.ExerciseService;
import com.marlonreina.PersonalFitnessTracker.domain.model.Exercise;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.request.ExerciseRegisterRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.response.ExerciseDTO;
import org.springframework.stereotype.Service;


@Service
public class ExerciseAddUseCase {
     private final ExerciseService exerciseService;

    public ExerciseAddUseCase(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    public ExerciseDTO execute (ExerciseRegisterRequest request) {
        Exercise exercise = new Exercise(
                null,
                request.getName(),
                request.getDescription()
            );

        Exercise savedExercise = exerciseService.registerExercise(exercise);

        return new ExerciseDTO(
                savedExercise.getId(),
                savedExercise.getName(),
                savedExercise.getDescription()
        );
    }
}
