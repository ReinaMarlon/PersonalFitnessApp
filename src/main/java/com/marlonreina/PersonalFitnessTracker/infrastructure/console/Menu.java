package com.marlonreina.PersonalFitnessTracker.infrastructure.console;

import com.marlonreina.PersonalFitnessTracker.application.usecase.exercise.ExerciseAddUseCase;
import com.marlonreina.PersonalFitnessTracker.application.usecase.exercise.ExerciseDeleteUseCase;
import com.marlonreina.PersonalFitnessTracker.application.usecase.exercise.ExerciseListAllUseCase;
import com.marlonreina.PersonalFitnessTracker.application.usecase.user.UserLoginUseCase;
import com.marlonreina.PersonalFitnessTracker.application.usecase.user.UserRegisterUseCase;
import com.marlonreina.PersonalFitnessTracker.application.usecase.workout.WorkoutListAllLoggedUseCase;
import com.marlonreina.PersonalFitnessTracker.application.usecase.workout.WorkoutLogUseCase;
import com.marlonreina.PersonalFitnessTracker.application.usecase.workout.WorkoutsAllListUseCase;
import com.marlonreina.PersonalFitnessTracker.domain.model.User;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.request.ExerciseDeleteRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.request.ExerciseRegisterRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.exercise.response.ExerciseDTO;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request.RegisterUserRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request.UserLoginRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.response.UserDTO;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request.WorkoutListAllLoggedRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request.WorkoutLogExerciseRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.request.WorkoutLogRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workout.response.WorkoutDTO;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog.WorkoutLogDTO;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.workoutlog.WorkoutLogExerciseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class Menu implements CommandLineRunner {


    private final Validator validator;

    private final Scanner scanner = new Scanner(System.in);
    private final UserRegisterUseCase userRegisterUseCase;
    private final UserLoginUseCase userLoginUseCase;
    private final WorkoutsAllListUseCase workoutsAllListUseCase;
    private final WorkoutLogUseCase workoutLogUseCase;
    private final WorkoutListAllLoggedUseCase workoutsListAllLoggedUseCase;
    private final ExerciseListAllUseCase exerciseListAllUseCase;
    private final ExerciseAddUseCase exerciseAddUseCase;
    private final ExerciseDeleteUseCase exerciseDeleteUseCase;
    private UserDTO currentUser;


    public Menu(Validator validator, UserRegisterUseCase userRegisterUseCase, UserLoginUseCase userLoginUseCase, WorkoutsAllListUseCase workoutsAllListUseCase, WorkoutLogUseCase workoutLogUseCase, WorkoutListAllLoggedUseCase workoutListAllLoggedUseCase, ExerciseListAllUseCase exerciseListAllUseCase, ExerciseAddUseCase exerciseAddUseCase, ExerciseDeleteUseCase exerciseDeleteUseCase) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = (Validator) factory.getValidator();
        this.userRegisterUseCase = userRegisterUseCase;
        this.userLoginUseCase = userLoginUseCase;
        this.workoutsAllListUseCase = workoutsAllListUseCase;
        this.workoutLogUseCase = workoutLogUseCase;
        this.workoutsListAllLoggedUseCase = workoutListAllLoggedUseCase;
        this.exerciseListAllUseCase = exerciseListAllUseCase;
        this.exerciseAddUseCase = exerciseAddUseCase;
        this.exerciseDeleteUseCase = exerciseDeleteUseCase;
    }

    private boolean validateUser(RegisterUserRequest request) {
        Set<ConstraintViolation<RegisterUserRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<RegisterUserRequest> v : violations) {
                System.out.println("Error: " + v.getMessage());
            }
            return false;
        }
        return true;
    }

    @Override
    public void run(String... args) {
        boolean running = true;
        System.out.println("Welcome to the Personal Fitness Tracker!");

        while (running) {
            System.out.println("\n==========================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> {
                    System.out.println("Goodbye");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void register() {
        System.out.println("--- Register ---");

        RegisterUserRequest request = new RegisterUserRequest();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        try {
            request.setAge(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid age, must be a number.");
            return;
        }

        System.out.println("Sex (M/F): ");
        String sex = scanner.nextLine();
        if (!sex.equalsIgnoreCase("M") && !sex.equalsIgnoreCase("F")) {
            System.out.println("Invalid sex, must be M or F.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.println(email);

        System.out.print("Password: ");
        String password = scanner.nextLine();

        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setSex(sex);
        request.setEmail(email);
        request.setPassword(password);

        if (!validateUser(request)) {
            return;
        }

        try {
            var registeredUser = userRegisterUseCase.execute(request);
            System.out.println("User registered successfully: " + registeredUser.getEmail());
        } catch (Exception e) {
            System.out.println("Error registering user, please try again");
        }
    }

    private void login() {
        UserLoginRequest request = new UserLoginRequest();
        System.out.println("--- Login ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        request.setEmail(email);
        request.setPassword(password);

        boolean success = false;
        UserDTO loggedUser = null;

        try {
            loggedUser = userLoginUseCase.execute(request);
            System.out.println("Logged in successfully as " + loggedUser.getFirstName());
            this.currentUser = loggedUser;
            success = true;
        } catch (Exception e) {
            System.out.println("Error, incorrect email or password");
        }

        if (success) {
            switch (loggedUser.getRole()) {
                case ADMIN -> showAdminMenu();
                case REGULAR -> showMainMenu();
                default -> {
                    System.out.println("Unknown role, defaulting to regular menu.");
                    showMainMenu();
                }
            }
        }
    }



    public void showMainMenu() {
        boolean logged = true;

        while (logged) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Show Workouts");
            System.out.println("2. Log Workout");
            System.out.println("3. View Logged Workouts");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> showWorkouts();
                case "2" -> logWorkout();
                case "3" -> viewLoggedWorkouts();
                case "4" -> {
                    System.out.println("Logged out.");
                    logged = false;
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    public void showAdminMenu() {
        boolean logged = true;

        while (logged) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Show Exercises");
            System.out.println("2. Add Exercise");
            System.out.println("3. Delete Exercise");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> showExercises();
                case "2" -> addExercise();
                case "3" -> deleteExercise();
                case "4" -> {
                    System.out.println("Logged out.");
                    logged = false;
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }


    private void showWorkouts() {
        boolean viewing = true;

        while (viewing) {
            List<WorkoutDTO> workouts = workoutsAllListUseCase.execute();

            System.out.println("Available Workouts:");
            for (WorkoutDTO w : workouts) {
                System.out.println(w.getId() + ". " + w.getName() + " - " + w.getDescription());
            }

            System.out.println("Enter the number of workout to view its structure, or type 'back' to return:");
            String input = scanner.nextLine();

            if ("back".equalsIgnoreCase(input)) {
                viewing = false;
            } else {
                try {
                    Long workoutId = Long.parseLong(input);
                    WorkoutDTO selected = workouts.stream()
                            .filter(w -> w.getId().equals(workoutId))
                            .findFirst()
                            .orElse(null);

                    if (selected != null) {
                        String[] exerciseList = selected.getExercises().stream()
                                .map(ex -> ex.getExercise().getName() + ": " + ex.getData().getSet() +
                                        " sets of " + ex.getData().getReps() + " reps")
                                .toArray(String[]::new);

                        showWorkoutStructure(
                                selected.getName(),
                                selected.getDescription(),
                                exerciseList,
                                selected.getNotes()
                        );
                    } else {
                        System.out.println("Invalid workout ID.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number or 'back'.");
                }
            }
        }
    }

    private void showWorkoutStructure(String title, String description, String[] exercises, String notes) {
        System.out.println("\n==========================");
        System.out.println("Workout Structure: " + title);
        System.out.println("Description: " + description);
        System.out.println("\nExercises:");
        for (String ex : exercises) {
            System.out.println("- " + ex);
        }
        System.out.println("\nNotes: " + notes);
        System.out.print("\nPress ENTER to return to the workout list...");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    private void logWorkout() {
        WorkoutLogRequest workoutLogRequest = new WorkoutLogRequest();

        List<WorkoutDTO> workouts = workoutsAllListUseCase.execute();
        System.out.println("Available Workouts:");
        for (WorkoutDTO w : workouts) {
            System.out.println(w.getId() + ". " + w.getName());
        }
        System.out.println("Enter the number of the workout you want to log, or 'back' to return:");
        String input = scanner.nextLine();

        if ("back".equalsIgnoreCase(input)) {
            return;
        }

        Long workoutId;
        try {
            workoutId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Returning to menu...");
            return;
        }

        WorkoutDTO selected = workouts.stream()
                .filter(w -> w.getId().equals(workoutId))
                .findFirst()
                .orElse(null);

        if (selected == null) {
            System.out.println("Workout not found.");
            return;
        }

        workoutLogRequest.setWorkoutId(selected.getId());
        workoutLogRequest.setUserId(currentUser.getId());

        List<WorkoutLogExerciseRequest> exerciseRequests = new ArrayList<>();

        System.out.println("\nRegister the result of your workout: " + selected.getName());

        selected.getExercises().forEach(ex -> {
            System.out.print("Enter time taken (minutes) for " + ex.getExercise().getName() + ": ");
            try {
                int timeTaken = Integer.parseInt(scanner.nextLine());
                WorkoutLogExerciseRequest exRequest = new WorkoutLogExerciseRequest();
                exRequest.setExerciseId(ex.getExercise().getId());
                exRequest.setTimeTaken(timeTaken);
                exerciseRequests.add(exRequest);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, skipping exercise...");
            }
        });

        workoutLogRequest.setExercises(exerciseRequests);

        int totalTime = exerciseRequests.stream()
                .mapToInt(WorkoutLogExerciseRequest::getTimeTaken)
                .sum();
        workoutLogRequest.setTotalTime(totalTime);

        int calories = totalTime * 5;
        workoutLogRequest.setCaloriesBurned(calories);

        workoutLogUseCase.execute(workoutLogRequest);

        System.out.println("Workout logged! Total time: " + totalTime + " mins, Calories burned: " + calories);

    }

    private void viewLoggedWorkouts(){
        WorkoutListAllLoggedRequest workoutsListAllLoggedRequest = new WorkoutListAllLoggedRequest();

        workoutsListAllLoggedRequest.setUserId(currentUser.getId());

        List<WorkoutLogDTO> loggedWorkouts = workoutsListAllLoggedUseCase.execute(workoutsListAllLoggedRequest);

        if (loggedWorkouts.isEmpty()) {
            System.out.println("No workouts logged yet.");
            return;
        }

        System.out.println("Workout History (Sorted by Date Descending):");
        int index = 1;
        for (WorkoutLogDTO w : loggedWorkouts) {
            System.out.println(index + ". Date: " + w.getDate());
            System.out.println("   Workout: " + w.getWorkoutName());
            index++;
        }

        System.out.println("\nEnter a number to a workout to view more details, or type 'back' to return:");
        String input = scanner.nextLine();

        if ("back".equalsIgnoreCase(input)) {
            return;
        }

        try {
            int selectedIndex = Integer.parseInt(input);

            if (selectedIndex < 1 || selectedIndex > loggedWorkouts.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            WorkoutLogDTO selectedWorkout = loggedWorkouts.get(selectedIndex - 1);

            System.out.println("\nWorkout Details:");
            System.out.println("Date: " + selectedWorkout.getDate());
            System.out.println("Workout: " + selectedWorkout.getWorkoutName());
            System.out.println("Total Time: " + selectedWorkout.getTotalTime() + " minutes");
            System.out.println("Calories Burned: " + selectedWorkout.getCaloriesBurned());

            if (selectedWorkout.getExercises() != null && !selectedWorkout.getExercises().isEmpty()) {
                System.out.println("Exercises:");
                for (WorkoutLogExerciseDTO ex : selectedWorkout.getExercises()) {
                    System.out.println(" - " + ex.getExerciseName() + " | Time: " + ex.getTimeTaken() + " min");
                }
            } else {
                System.out.println("No exercises logged.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }

    }

    private void showExercises() {
        boolean viewing = true;

        while (viewing) {
            List<ExerciseDTO> exercises = exerciseListAllUseCase.execute();

            if (exercises.isEmpty()) {
                System.out.println("No exercises available.");
                return;
            }

            System.out.println("\nAvailable Exercises:");
            for (ExerciseDTO e : exercises) {
                System.out.println(e.getId() + ". " + e.getName() + " - " + e.getDescription());
            }

            System.out.println("\nEnter the number of exercise to view its details, or type 'back' to return:");
            String input = scanner.nextLine();

            if ("back".equalsIgnoreCase(input)) {
                viewing = false;
            } else {
                try {
                    Long exerciseId = Long.parseLong(input);
                    ExerciseDTO selected = exercises.stream()
                            .filter(e -> e.getId().equals(exerciseId))
                            .findFirst()
                            .orElse(null);

                    if (selected != null) {
                        System.out.println("\n==========================");
                        System.out.println("Exercise Details");
                        System.out.println("Name: " + selected.getName());
                        System.out.println("Description: " + selected.getDescription());

                        System.out.print("\nPress ENTER to return to exercise list...");
                        scanner.nextLine();
                    } else {
                        System.out.println("Invalid exercise ID.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number or 'back'.");
                }
            }
        }
    }

    private void addExercise() {

        ExerciseRegisterRequest exerciseRegisterRequest = new ExerciseRegisterRequest();

        System.out.println("--- Add Exercise ---");

        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            exerciseRegisterRequest.setName(name);
            exerciseRegisterRequest.setDescription(description);


            ExerciseDTO created = exerciseAddUseCase.execute(exerciseRegisterRequest);

            System.out.println("Exercise added successfully: " + created.getName());

        } catch (Exception e) {
            System.out.println("Error adding exercise: " + e.getMessage());
        }
    }

    private void deleteExercise() {
        System.out.println("--- Delete Exercise ---");

        List<ExerciseDTO> exercises = exerciseListAllUseCase.execute();

        if (exercises.isEmpty()) {
            System.out.println("No exercises available to delete.");
            return;
        }

        System.out.println("Available Exercises:");
        for (ExerciseDTO e : exercises) {
            System.out.println(e.getId() + ". " + e.getName() + " - " + e.getDescription());
        }

        System.out.print("\nEnter the ID of the exercise to delete, or type 'back' to return: ");
        String input = scanner.nextLine();

        if ("back".equalsIgnoreCase(input)) {
            return;
        }

        try {
            Long exerciseId = Long.parseLong(input);

            ExerciseDTO selected = exercises.stream()
                    .filter(e -> e.getId().equals(exerciseId))
                    .findFirst()
                    .orElse(null);

            if (selected == null) {
                System.out.println("❌ Invalid exercise ID.");
                return;
            }

            ExerciseDeleteRequest request = new ExerciseDeleteRequest();
            request.setId(exerciseId);

            ExerciseDTO deleted = exerciseDeleteUseCase.execute(request);
            System.out.println("Exercise deleted successfully: " + selected.getName());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
        } catch (Exception e) {
            System.out.println("Error deleting exercise: " + e.getMessage());
        }
    }

}
