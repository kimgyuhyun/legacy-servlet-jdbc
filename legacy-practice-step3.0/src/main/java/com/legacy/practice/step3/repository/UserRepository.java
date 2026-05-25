package com.legacy.practice.step3.repository;

import com.legacy.practice.step3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);

    List<User> findAll();

    @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN FETCH u.detail " +
            "WHERE u.id = :id")
    Optional<User> findUserWithDetailById(@Param("id") Long id);


    @Query("SELECT DISTINCT u " +
            "FROM User u " +
            "LEFT JOIN FETCH u.detail ")
    List<User> findAllUserWithDetailList();

//    @Query("SELECT DISTINCT u " +
//            "FROM User u " +
//            "INNER JOIN FETCH u.detail ")
//    List<User> findAllUserWithDetailList();
}
