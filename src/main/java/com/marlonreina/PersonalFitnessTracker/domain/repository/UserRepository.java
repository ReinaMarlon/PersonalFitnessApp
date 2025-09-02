package com.marlonreina.PersonalFitnessTracker.domain.repository;

import com.marlonreina.PersonalFitnessTracker.domain.model.Role;
import com.marlonreina.PersonalFitnessTracker.domain.model.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@NonNullApi
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    List<User> findByRole(Role role);

}