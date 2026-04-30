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
        String sql = "UPDATE `user` SET name = ?, age = ?, birth_date = ?, address = ? WHERE id = ?";

        return jdbcTemplate.update(
                sql,
                dto.getName(),
                dto.getAge(),
                dto.getBirthDate(),
                dto.getAddress(),
                dto.getId()
        );
    }

    public int updateNameById(Long id, String name) {
        String sql = "UPDATE `user` SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, id);
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM `user` WHERE id = ?";

        return jdbcTemplate.update(sql, id);

    }

    public List<UserDto> findByNameAndAddress(String name, String address) {
        String sql = "SELECT id, name, age, birth_date, address " +
                     "FROM `user` " +
                     "WHERE name LIKE ? AND address LIKE ? " +
                     "ORDER BY id ASC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
           UserDto dto = new UserDto();
           dto.setId(rs.getLong("id"));
           dto.setName(rs.getString("name"));
           dto.setAge(rs.getInt("age"));
           dto.setBirthDate(rs.getDate("birth_date"));
           dto.setAddress(rs.getString("address"));
           return dto;
        }, "%" + name + "%", "%" + address + "%"
        );
    }


    public List<UserDto> findDynamicByNameAndAddress(String name, String address) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, age, birth_date, address ")
                .append("FROM `user` ")
                .append("WHERE 1=1 ");

        List<Object> params = new ArrayList<>();


        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ? ");
            params.add("%" + name + "%");
        }

        if (address != null && !address.trim().isEmpty()) {
            sql.append(" AND address LIKE ? ");
            params.add("%" + address + "%");
        }

        if (params.isEmpty()) {
            return emptyList();
        }

        sql.append(" ORDER BY id ASC");

        return jdbcTemplate.query(
                sql.toString(),
                (rs, rowNum) -> {
                    UserDto dto = new UserDto();
                    dto.setId(rs.getLong("id"));
                    dto.setName(rs.getString("name"));
                    dto.setAge(rs.getInt("age"));
                    dto.setBirthDate(rs.getDate("birth_date"));
                    dto.setAddress(rs.getString("address"));
                    return dto;
                },
                params.toArray()
        );

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
