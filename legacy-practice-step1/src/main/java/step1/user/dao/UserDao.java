package step1.user.dao;

import step1.user.dto.UserDto;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UserDao {
	
    private final ServletContext servletContext;
    
    public UserDao(ServletContext servletContext) {
    	this.servletContext = servletContext;
    }
    
    private Connection getConnection() throws Exception {
    	Properties props = new Properties();
    	
    	try(InputStream in = servletContext.getResourceAsStream("/WEB-INF/db.properties")) {
    		if (in == null) {
    			throw new IllegalStateException("/WEB-INF/db.properties not found");
    		}
    		props.load(in);
    	}
    	
    	String url = props.getProperty("jdbc.url");
    	String username = props.getProperty("jdbc.username");
    	String password = props.getProperty("jdbc.password");
    
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	return DriverManager.getConnection(url, username, password);
    }
    
    public int insert(UserDto dto) throws Exception {
    	String sql = "INSERT INTO `user` (name, age, birth_date, address) VALUES (?, ?, ?, ?)";
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(sql);
    		
    		pstmt.setString(1, dto.getName());
    		pstmt.setInt(2, dto.getAge());
    		pstmt.setDate(3, dto.getBirthDate());
    		pstmt.setString(4, dto.getAddress());
    		
    		return pstmt.executeUpdate();
    	} finally {
    		if (pstmt != null) {
    			try { 
    				pstmt.close(); 
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    		}
    		if (conn != null) {
    			try { 
    				conn.close(); 
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    		}
    	}
    }
    
    public List<UserDto> findAll() throws Exception {
    	String sql = "SELECT id, name, age, birth_date, address, create_at, update_at FROM `user` ORDER BY id DESC";
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
            List<UserDto> result = new ArrayList<>();
            while (rs.next()) {
            	UserDto dto = new UserDto();
            	dto.setId(rs.getLong("id"));
            	dto.setName(rs.getString("name"));
            	dto.setAge(rs.getInt("age"));
            	dto.setBirthDate(rs.getDate("birth_date"));
            	dto.setAddress(rs.getString("address"));
            	dto.setCreateAt(rs.getTimestamp("create_at"));
            	dto.setUpdateAt(rs.getTimestamp("update_at"));
            	result.add(dto);
            }
            return result;
    	} finally {
    		if (rs != null) {
    			try {
    				rs.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		
    		if (pstmt != null) {
    			try {
    				pstmt.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		
    		if (conn != null) {
    			try {
    				conn.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }

}
