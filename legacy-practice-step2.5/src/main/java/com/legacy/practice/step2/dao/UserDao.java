package com.legacy.practice.step2.dao;

import com.legacy.practice.step2.dto.UserDetailDto;
import com.legacy.practice.step2.dto.UserDto;
import com.legacy.practice.step2.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public int updateDynamicNameAddressById(UserDto dto) {
        return userMapper.updateDynamicNameAddressById(dto);
    }

    public int deleteById(Long id) {
        return userMapper.deleteById(id);
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

    public List<UserDto> findUserByIdList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return emptyList();
        }
        return userMapper.findUserByIdList(idList);
    }

    public int deleteByIdList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return 0;
        }
        return userMapper.deleteByIdList(idList);
    }

    public long countAll() {
        return userMapper.contAll();
    }
    public List<UserDto> findUserListByPaged(int offset, int limit) {
        return userMapper.findAllPaged(offset, limit);
    }

}
