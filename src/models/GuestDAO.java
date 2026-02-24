package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    public List<Guest> getGuestsByUser(String userMobile) throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT guest_id, guest_name, guest_mobile, with_family FROM guests WHERE user_mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userMobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Guest guest = new Guest(
                            rs.getInt("guest_id"),
                            userMobile,
                            rs.getString("guest_name"),
                            rs.getString("guest_mobile"),
                            rs.getBoolean("with_family"));
                    guests.add(guest);
                }
            }
        }
        return guests;
    }

    public int addGuest(Guest guest) throws SQLException {
        String sql = "INSERT INTO guests (user_mobile, guest_name, guest_mobile, with_family) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, guest.getUserMobile());
            pstmt.setString(2, guest.getName());
            pstmt.setString(3, guest.getMobile());
            pstmt.setBoolean(4, guest.isWithFamily());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        }
        return -1;
    }

    public boolean updateGuest(Guest guest) throws SQLException {
        String sql = "UPDATE guests SET guest_name = ?, with_family = ? WHERE guest_id = ? AND user_mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, guest.getName());
            pstmt.setBoolean(2, guest.isWithFamily());
            pstmt.setInt(3, guest.getId());
            pstmt.setString(4, guest.getUserMobile());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteGuest(int guestId, String userMobile) throws SQLException {
        String sql = "DELETE FROM guests WHERE guest_id = ? AND user_mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guestId);
            pstmt.setString(2, userMobile);
            return pstmt.executeUpdate() > 0;
        }
    }
}
