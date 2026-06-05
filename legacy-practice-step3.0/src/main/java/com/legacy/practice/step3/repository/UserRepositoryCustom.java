package com.legacy.practice.step3.repository;

import com.legacy.practice.step3.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findAllByQueryDSL();
}
