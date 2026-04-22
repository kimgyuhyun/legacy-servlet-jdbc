package com.legacy.practice.step2.dao;

import com.legacy.practice.step2.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


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

//    public List<UserDto> findAll() throws Exception {
//        String sql = "SELECT id, name, age, birth_date, address, create_at, update_at FROM `user` ORDER BY id DESC";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            List<UserDto> result = new ArrayList<>();
//            while (rs.next()) {
//                UserDto dto = new UserDto();
//                dto.setId(rs.getLong("id"));
//                dto.setName(rs.getString("name"));
//                dto.setAge(rs.getInt("age"));
//                dto.setBirthDate(rs.getDate("birth_date"));
//                dto.setAddress(rs.getString("address"));
//                dto.setCreateAt(rs.getTimestamp("create_at"));
//                dto.setUpdateAt(rs.getTimestamp("update_at"));
//                result.add(dto);
//            }
//            return result;
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
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
//    public UserDto findById(Long id) throws Exception {
//        String sql = "SELECT id, name, age, birth_date, address, create_at, update_at FROm `user` WHERE id = ?";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setLong(1, id);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                UserDto dto = new UserDto();
//                dto.setId(rs.getLong("id"));
//                dto.setName(rs.getString("name"));
//                dto.setAge(rs.getInt("age"));
//                dto.setBirthDate(rs.getDate("birth_date"));
//                dto.setAddress(rs.getString("address"));
//                dto.setCreateAt(rs.getTimestamp("create_at"));
//                dto.setUpdateAt(rs.getTimestamp("update_at"));
//                return dto;
//            }
//
//            return null;
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
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
