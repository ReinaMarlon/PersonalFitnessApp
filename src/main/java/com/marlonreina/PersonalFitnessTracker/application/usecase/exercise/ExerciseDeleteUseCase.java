package com.marlonreina.PersonalFitnessTracker.application.usecase.exercise;

import com.marlonreina.PersonalFitnessTracker.application.service.ExerciseService;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.request.ExerciseDeleteRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.response.ExerciseDTO;
import org.springframework.stereotype.Service;

@Service
public class ExerciseDeleteUseCase {
    private ExerciseService exerciseService;

    public ExerciseDeleteUseCase(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    public ExerciseDTO execute(ExerciseDeleteRequest request) {
        return exerciseService.deleteExercise(request.getId());
    }
}
