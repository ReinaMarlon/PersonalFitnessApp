package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EditUserDataRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String sex;
}
