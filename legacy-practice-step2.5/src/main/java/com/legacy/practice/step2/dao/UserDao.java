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
        String sql = "SELECT " +
                "d.id AS id, " +
                "u.id AS userId, " +
                "d.phone AS phone, " +
                "d.job AS job, " +
                "d.create_at AS createAt, " +
                "d.update_at AS updateAt " +
                "FROM user u " +
                "LEFT JOIN user_detail d ON u.id = d.user_id " +
                "WHERE u.id = ?";

        List<UserDetailDto> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserDetailDto dto = new UserDetailDto();
            dto.setId(rs.getObject("id", Long.class));
            dto.setUserId(rs.getLong("userId"));
            dto.setPhone(rs.getString("phone"));
            dto.setJob(rs.getString("job"));
            dto.setCreateAt(rs.getTimestamp("createAt"));
            dto.setUpdateAt(rs.getTimestamp("updateAt"));
            return dto;
        }, userId);

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

}
