package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private String sex;
    private String email;
    private String password;
}
