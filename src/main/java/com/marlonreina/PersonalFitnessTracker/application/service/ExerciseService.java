package com.marlonreina.PersonalFitnessTracker.application.service;

import com.marlonreina.PersonalFitnessTracker.domain.model.Exercise;
import com.marlonreina.PersonalFitnessTracker.domain.repository.ExerciseRepository;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.response.ExerciseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    //Dependency Injection
    public ExerciseService(ExerciseRepository exerciseRepository){ this.exerciseRepository = exerciseRepository; }

    public Exercise registerExercise(Exercise exercise) {
        if(exerciseRepository.existsByName(exercise.getName())) {
            throw new IllegalArgumentException("That exercise already exists");
        }
        return exerciseRepository.save(exercise);
    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(ex -> {
                    ExerciseDTO dto = new ExerciseDTO();
                    dto.setId(ex.getId());
                    dto.setName(ex.getName());
                    dto.setDescription(ex.getDescription());
                    return dto;
                })
                .toList();
    }

    public ExerciseDTO deleteExercise(Long id) {
        return exerciseRepository.findById(id)
                .map(ex -> {
                    exerciseRepository.deleteById(id);
                    ExerciseDTO dto = new ExerciseDTO();
                    dto.setId(ex.getId());
                    dto.setName(ex.getName());
                    dto.setDescription(ex.getDescription());
                    return dto;
                })
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found."));
    }


}
