package com.durgasoft.Repository;

import com.durgasoft.Entity.Signup;
import jakarta.validation.constraints.Email;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupRepository extends JpaRepository<Signup, Long> {


    Optional<Signup> findByEmail(String email);
    Optional<Signup> findByLogin(String login);
    Optional<Signup> findByMobile(String mobile);

    Optional<Signup> findByUsername(String username);
}