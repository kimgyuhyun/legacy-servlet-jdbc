package com.legacy.practice.step2.mapper;

import com.legacy.practice.step2.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<UserDto> findAll();
    UserDto findById(Long id);
    int insert(UserDto dto);
    int updateById(UserDto dto);
    int updateNameById(@Param("id") Long id, @Param("name") String name);
    List<UserDto> findByNameAndAddress(
            @Param("name")String name, @Param("address") String address);
}
