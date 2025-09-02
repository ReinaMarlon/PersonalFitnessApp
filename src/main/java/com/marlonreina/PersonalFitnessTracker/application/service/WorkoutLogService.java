package com.marlonreina.PersonalFitnessTracker.application.service;

import com.marlonreina.PersonalFitnessTracker.domain.model.WorkoutLog;
import com.marlonreina.PersonalFitnessTracker.domain.model.WorkoutLogExercise;
import com.marlonreina.PersonalFitnessTracker.domain.repository.ExerciseRepository;
import com.marlonreina.PersonalFitnessTracker.domain.repository.UserRepository;
import com.marlonreina.PersonalFitnessTracker.domain.repository.WorkoutRepository;
import com.marlonreina.PersonalFitnessTracker.domain.repository.WorkoutLogRepository;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request.WorkoutLogRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request.WorkoutLogExerciseRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog.WorkoutLogDTO;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog.WorkoutLogExerciseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutLogService {

    private final WorkoutLogRepository workoutLogRepository;
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutLogService(
            WorkoutLogRepository workoutLogRepository,
            WorkoutRepository workoutRepository,
            UserRepository userRepository,
            ExerciseRepository exerciseRepository
    ) {
        this.workoutLogRepository = workoutLogRepository;
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public WorkoutLogDTO registerWorkoutLog(WorkoutLogRequest request) {
        var user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var workout = workoutRepository.findById(request.getWorkoutId())
                .orElseThrow(() -> new RuntimeException("Workout not found"));


        int totalTime = request.getExercises().stream()
                .mapToInt(WorkoutLogExerciseRequest::getTimeTaken)
                .sum();

        WorkoutLog workoutLog = new WorkoutLog();
        workoutLog.setUserReference(user);
        workoutLog.setWorkoutReference(workout);
        workoutLog.setTotalTime(totalTime);
        workoutLog.setCaloriesBurned(request.getCaloriesBurned());

        List<WorkoutLogExercise> logExercises = request.getExercises().stream()
                .map(exReq -> {
                    var exercise = exerciseRepository.findById(exReq.getExerciseId())
                            .orElseThrow(() -> new RuntimeException("Exercise not found"));

                    WorkoutLogExercise logExercise = new WorkoutLogExercise();
                    logExercise.setWorkoutLog(workoutLog);
                    logExercise.setExercise(exercise);
                    logExercise.setTimeTaken(exReq.getTimeTaken());
                    return logExercise;
                })
                .collect(Collectors.toList());

        workoutLog.setExercises(logExercises);


        WorkoutLog savedLog = workoutLogRepository.save(workoutLog);

        WorkoutLogDTO dto = new WorkoutLogDTO();
        dto.setId(savedLog.getId());
        dto.setWorkoutId(savedLog.getWorkoutReference().getId());
        dto.setUserId(savedLog.getUserReference().getId());
        dto.setTotalTime(savedLog.getTotalTime());
        dto.setCaloriesBurned(savedLog.getCaloriesBurned());
        dto.setDate(savedLog.getDate());


        List<WorkoutLogExerciseDTO> exerciseDTOs = savedLog.getExercises().stream()
                .map(e -> {
                    WorkoutLogExerciseDTO exDto = new WorkoutLogExerciseDTO();
                    exDto.setExerciseId(e.getExercise().getId());
                    exDto.setExerciseName(e.getExercise().getName());
                    exDto.setTimeTaken(e.getTimeTaken());
                    return exDto;
                })
                .collect(Collectors.toList());

        return dto;
    }

    @Transactional(readOnly = true)
    public List<WorkoutLogDTO> getAllLoggedWorkoutsByUser(Long userId) {

        return workoutLogRepository.findAll().stream()
                .filter(log -> log.getUserReference().getId().equals(userId))
                .map(log -> {
                    WorkoutLogDTO dto = new WorkoutLogDTO();
                    dto.setId(log.getId());
                    dto.setWorkoutId(log.getWorkoutReference().getId());
                    dto.setWorkoutName(log.getWorkoutReference().getName());
                    dto.setUserId(log.getUserReference().getId());
                    dto.setTotalTime(log.getTotalTime());
                    dto.setCaloriesBurned(log.getCaloriesBurned());
                    dto.setDate(log.getDate());

                    List<WorkoutLogExerciseDTO> exDtos = log.getExercises().stream().map(e -> {
                        WorkoutLogExerciseDTO exDto = new WorkoutLogExerciseDTO();
                        exDto.setExerciseId(e.getExercise().getId());
                        exDto.setExerciseName(e.getExercise().getName());
                        exDto.setTimeTaken(e.getTimeTaken());
                        return exDto;
                    }).collect(Collectors.toList());
                    dto.setExercises(exDtos);
                    return dto;
                }).collect(Collectors.toList());
    }
}
