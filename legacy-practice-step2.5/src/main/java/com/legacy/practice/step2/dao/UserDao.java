package com.legacy.practice.step2.dao;

import com.legacy.practice.step2.dto.UserDetailDto;
import com.legacy.practice.step2.dto.UserDto;
import com.legacy.practice.step2.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserDao(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    public int insert(UserDto dto) {
        return userMapper.insert(dto);
    }

    public List<UserDto> findAll() {
        return userMapper.findAll();
    }


    public UserDto findById(Long id) {
        return userMapper.findById(id);
    }

    public int updateById(UserDto dto) {
        return userMapper.updateById(dto);
    }

    public int updateNameById(Long id, String name) {
        return userMapper.updateNameById(id, name);
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM `user` WHERE id = ?";

        return jdbcTemplate.update(sql, id);

    }

    public List<UserDto> findByNameAndAddress(String name, String address) {
        return userMapper.findByNameAndAddress(name, address);
    }


    public List<UserDto> findDynamicByNameAndAddress(String name, String address) {
        return userMapper.findDynamicByNameAndAddress(name, address);
    }


    public UserDetailDto findDetailByUserId(Long userId) {
        return userMapper.findDetailByUserId(userId);
    }

}
