package com.marlonreina.PersonalFitnessTracker.application.usecase.user;

import com.marlonreina.PersonalFitnessTracker.application.service.UserService;
import com.marlonreina.PersonalFitnessTracker.domain.model.User;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request.EditUserDataRequest;
import com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.response.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class EditUserDataUseCase {

    private final UserService userService;

    public EditUserDataUseCase( UserService userService) { this.userService = userService; }

    public UserDTO execute(EditUserDataRequest request) {
        User user = userService.getUserById(request.getId())
                .orElseThrow(() -> new RuntimeException("User's Id not found"));

        user.setFirstName(request.getFirstName());
        user.setFirstName(request.getLastName());
        user.setAge(request.getAge());
        user.setSex(request.getSex());

        User updatedUser = userService.save(user);

        return new UserDTO(
                updatedUser.getId(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getEmail(),
                updatedUser.getAge(),
                updatedUser.getSex(),
                updatedUser.getRole()
        );
    }
}
