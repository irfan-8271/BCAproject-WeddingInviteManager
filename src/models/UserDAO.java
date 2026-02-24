package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (mobile, name, age, gender, password, security_question, security_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getMobile());
            pstmt.setString(2, user.getName());
            pstmt.setInt(3, user.getAge());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getSecurityQuestion());
            pstmt.setString(7, user.getSecurityAnswer());
            return pstmt.executeUpdate() > 0;
        }
    }

    public User loginUser(String mobile, String password) throws SQLException {
        String sql = "SELECT mobile, name FROM users WHERE mobile = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mobile);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setMobile(rs.getString("mobile"));
                    user.setName(rs.getString("name"));
                    return user;
                }
            }
        }
        return null; // Invalid credentials
    }

    public User getUserByMobile(String mobile) throws SQLException {
        String sql = "SELECT name, age, gender, security_question, security_answer FROM users WHERE mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setMobile(mobile);
                    user.setName(rs.getString("name"));
                    user.setAge(rs.getObject("age") != null ? rs.getInt("age") : 22);
                    user.setGender(rs.getString("gender"));
                    user.setSecurityQuestion(rs.getString("security_question"));
                    user.setSecurityAnswer(rs.getString("security_answer"));
                    return user;
                }
            }
        }
        return null;
    }

    public boolean updateUserProfile(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, age = ?, gender = ?, security_question = ?, security_answer = ? WHERE mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getGender());
            pstmt.setString(4, user.getSecurityQuestion());
            pstmt.setString(5, user.getSecurityAnswer());
            pstmt.setString(6, user.getMobile());
            return pstmt.executeUpdate() > 0;
        }
    }

    public User getSecurityDetails(String mobile) throws SQLException {
        String sql = "SELECT name, security_question FROM users WHERE mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setMobile(mobile);
                    user.setName(rs.getString("name"));
                    user.setSecurityQuestion(rs.getString("security_question"));
                    return user;
                }
            }
        }
        return null;
    }

    public String getPasswordBySecurityAnswer(String mobile, String answer) throws SQLException {
        String sql = "SELECT password FROM users WHERE mobile = ? AND security_answer = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mobile);
            pstmt.setString(2, answer);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        }
        return null;
    }

    public boolean checkUserExists(String mobile) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
