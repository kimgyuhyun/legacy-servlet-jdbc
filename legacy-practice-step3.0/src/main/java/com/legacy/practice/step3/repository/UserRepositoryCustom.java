package com.legacy.practice.step3.repository;

import com.legacy.practice.step3.dto.UserResponse;
import com.legacy.practice.step3.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    List<User> findAllByQueryDSL();

    Optional<User> findUserWithDetailByIdDSL(Long id);

    List<User> findAllUserWithDetailListDSL();

    List<User> searchUserList(String name, Integer age, String address);

    List<UserResponse> findAllUserResponseByProjection();
}
