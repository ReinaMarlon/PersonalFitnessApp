package com.marlonreina.PersonalFitnessTracker.application.usecase.user;

import com.marlonreina.PersonalFitnessTracker.domain.model.User;
import com.marlonreina.PersonalFitnessTracker.application.service.UserService;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request.RegisterUserRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.response.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterUseCase {

    private final UserService userService;

    public UserRegisterUseCase(UserService userService) { this.userService = userService; }

    public UserDTO execute(RegisterUserRequest request) {

        User user = new User(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getAge(),
                request.getSex(),
                request.getEmail(),
                request.getPassword(),
                null
        );

        User savedUser = userService.registerUser(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getAge(),
                savedUser.getSex(),
                savedUser.getRole()
        );

    }
}
