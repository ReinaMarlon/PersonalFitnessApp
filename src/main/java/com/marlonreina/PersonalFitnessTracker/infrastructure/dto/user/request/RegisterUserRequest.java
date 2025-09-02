package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private String sex;

    @Email(message = "Email must be valid (e.g., user@domain.com)")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,20}$",
            message = "The password must include (A-z,123,@$!%*?&._-)"
    )
    private String password;
}
