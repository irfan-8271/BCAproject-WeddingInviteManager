package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvitationDAO {

    public int getCurrentInvitationId(String userMobile) throws SQLException {
        String sql = "SELECT invitation_id FROM invitations WHERE user_mobile = ? ORDER BY last_updated DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userMobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("invitation_id");
                }
            }
        }
        return -1;
    }

    public Invitation getInvitation(String userMobile) throws SQLException {
        String sql = "SELECT invitation_id, bride_name, groom_name, event_date, event_time, venue, address, dress_code "
                +
                "FROM invitations WHERE user_mobile = ? ORDER BY last_updated DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userMobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Invitation inv = new Invitation();
                    inv.setId(rs.getInt("invitation_id"));
                    inv.setUserMobile(userMobile);
                    inv.setBrideName(rs.getString("bride_name"));
                    inv.setGroomName(rs.getString("groom_name"));
                    inv.setEventDate(rs.getString("event_date"));
                    inv.setEventTime(rs.getString("event_time"));
                    inv.setVenue(rs.getString("venue"));
                    inv.setAddress(rs.getString("address"));
                    inv.setDressCode(rs.getString("dress_code"));
                    return inv;
                }
            }
        }
        return null; // Not found
    }

    public boolean updateInvitation(Invitation inv) throws SQLException {
        String sql = "UPDATE invitations SET bride_name = ?, groom_name = ?, event_date = ?, event_time = ?, venue = ?, address = ?, dress_code = ? "
                +
                "WHERE invitation_id = ? AND user_mobile = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inv.getBrideName());
            pstmt.setString(2, inv.getGroomName());
            pstmt.setString(3, inv.getEventDate());
            pstmt.setString(4, inv.getEventTime());
            pstmt.setString(5, inv.getVenue());
            pstmt.setString(6, inv.getAddress());
            pstmt.setString(7, inv.getDressCode());
            pstmt.setInt(8, inv.getId());
            pstmt.setString(9, inv.getUserMobile());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean insertInvitation(Invitation inv) throws SQLException {
        String sql = "INSERT INTO invitations (user_mobile, bride_name, groom_name, event_date, event_time, venue, address, dress_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inv.getUserMobile());
            pstmt.setString(2, inv.getBrideName());
            pstmt.setString(3, inv.getGroomName());
            pstmt.setString(4, inv.getEventDate());
            pstmt.setString(5, inv.getEventTime());
            pstmt.setString(6, inv.getVenue());
            pstmt.setString(7, inv.getAddress());
            pstmt.setString(8, inv.getDressCode());
            return pstmt.executeUpdate() > 0;
        }
    }
}
