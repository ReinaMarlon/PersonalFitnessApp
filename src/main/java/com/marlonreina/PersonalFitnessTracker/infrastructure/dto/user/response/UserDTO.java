package com.marlonreina.PersonalFitnessTracker.infrastructure.dto.user.response;

import com.marlonreina.PersonalFitnessTracker.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String sex;
    private Role role;
}
