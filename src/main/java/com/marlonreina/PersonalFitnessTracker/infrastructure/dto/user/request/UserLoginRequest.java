package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLoginRequest {
    private String email;
    private String password;
}
