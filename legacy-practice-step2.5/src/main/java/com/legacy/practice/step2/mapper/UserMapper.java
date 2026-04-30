package com.legacy.practice.step2.mapper;

import com.legacy.practice.step2.dto.UserDto;

import java.util.List;

public interface UserMapper {
    List<UserDto> findAll();

    UserDto findById(Long id);
}
