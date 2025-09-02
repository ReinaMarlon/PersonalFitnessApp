package com.marlonreina.PersonalFitnessTracker.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor
public class User {
    //Personal params
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String sex;

    //Account Params
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,20}$",
            message = "The password must include (A-z123@$!%*?&._-)"
    )
    private String password;

    //Role param
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userReference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutLog> logs = new ArrayList<>();

    public User(Long id, String firstName, String lastName, Integer age, String sex, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
