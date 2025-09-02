package com.marlonreina.PersonalFitnessTracker.application.usecase.user;

import com.marlonreina.PersonalFitnessTracker.application.service.UserService;
import com.marlonreina.PersonalFitnessTracker.domain.model.User;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request.UserLoginRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.response.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserLoginUseCase {

    private final UserService userService;

    public UserLoginUseCase(UserService userService) { this.userService = userService; }

    public UserDTO execute(UserLoginRequest request) {
            User user = userService.login(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new RuntimeException("Invalid email or password"));

            return new UserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getAge(),
                    user.getSex(),
                    user.getRole()
            );
    }
}
