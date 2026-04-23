package com.legacy.practice.step2.dao;

import com.legacy.practice.step2.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
        String sql = "SELECT id, name FROM `user` ORDER BY id ASC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserDto dto = new UserDto();
            dto.setId(rs.getLong("id"));
            dto.setName(rs.getString("name"));
            return dto;
        });
    }


    public UserDto findById(Long id) {
        String sql = "SELECT id, name, age, birth_date, address, create_at, update_at FRom `user` WHERE id = ?";
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
//
//    public int deleteById(Long id) throws Exception {
//        String sql = "DELETE FROM `user` WHERE id = ?";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setLong(1, id);
//
//            return pstmt.executeUpdate();
//        } finally {
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//

//
//    public int updateById(UserDto dto) throws Exception {
//        String sql = "UPDATE `user` SET name = ?, age = ?, birth_date = ?, address = ? WHERE id = ?";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, dto.getName());
//            pstmt.setInt(2, dto.getAge());
//            pstmt.setDate(3, dto.getBirthDate());
//            pstmt.setString(4, dto.getAddress());
//            pstmt.setLong(5, dto.getId());
//
//            return pstmt.executeUpdate();
//        } finally {
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
