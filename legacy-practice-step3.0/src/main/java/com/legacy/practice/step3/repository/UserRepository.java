package com.legacy.practice.step3.repository;

import com.legacy.practice.step3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);
    List<User> findAll();
}
