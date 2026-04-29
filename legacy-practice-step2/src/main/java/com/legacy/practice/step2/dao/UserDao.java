package com.legacy.practice.step2.dao;

import com.legacy.practice.step2.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(UserDto dto) {
        String sql = "INSERT INTO `user` (name, age, birth_date, address) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                dto.getName(),
                dto.getAge(),
                dto.getBirthDate(),
                dto.getAddress()
        );
    }

    public List<UserDto> findAll() {
        String sql = "SELECT id, name, age, birth_date, address FROM `user` ORDER BY id ASC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserDto dto = new UserDto();
            dto.setId(rs.getLong("id"));
            dto.setName(rs.getString("name"));
            dto.setAge(rs.getInt("age"));
            dto.setBirthDate(rs.getDate("birth_date"));
            dto.setAddress(rs.getString("address"));
            return dto;
        });
    }


    public UserDto findById(Long id) {
        String sql = "SELECT id, name, age, birth_date, address, create_at, update_at FROM `user` WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            UserDto dto = new UserDto();
            dto.setId(rs.getLong("id"));
            dto.setName(rs.getString("name"));
            dto.setAge(rs.getInt("age"));
            dto.setBirthDate(rs.getDate("birth_date"));
            dto.setAddress(rs.getString("address"));
            dto.setCreateAt(rs.getTimestamp("create_at"));
            dto.setUpdateAt(rs.getTimestamp("update_at"));
            return dto;
        }, id);
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
        }, "%" + name + "%",
                "%" + address + "%"
        );
    }


}
