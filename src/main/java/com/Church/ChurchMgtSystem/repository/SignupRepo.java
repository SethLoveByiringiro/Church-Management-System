package com.Church.ChurchMgtSystem.repository;

import com.Church.ChurchMgtSystem.model.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SignupRepo extends JpaRepository<Signup, Integer> {
    Optional<Signup> findByUsername(String username);
}
