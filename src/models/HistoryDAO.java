package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {

    public List<SentInvitation> getSentHistory(String userMobile) throws SQLException {
        List<SentInvitation> history = new ArrayList<>();
        String sql = "SELECT si.sent_timestamp, g.guest_name, g.guest_mobile, i.bride_name, i.groom_name, i.event_date "
                +
                "FROM sent_invitations si " +
                "JOIN guests g ON si.guest_id = g.guest_id " +
                "JOIN invitations i ON si.invitation_id = i.invitation_id " +
                "WHERE si.user_mobile = ? " +
                "ORDER BY si.sent_timestamp DESC";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userMobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    history.add(new SentInvitation(
                            rs.getTimestamp("sent_timestamp"),
                            rs.getString("guest_name"),
                            rs.getString("guest_mobile"),
                            rs.getString("bride_name"),
                            rs.getString("groom_name"),
                            rs.getString("event_date")));
                }
            }
        }
        return history;
    }

    public List<ReceivedInvitation> getReceivedInvitations(String userMobile) throws SQLException {
        List<ReceivedInvitation> receivedList = new ArrayList<>();
        String sql = "SELECT r.received_timestamp, r.status, " +
                "       i.invitation_id, i.bride_name, i.groom_name, i.event_date, i.event_time, i.venue, i.address, i.dress_code, "
                +
                "       u.name AS sender_name " +
                "FROM received_invitations r " +
                "JOIN invitations i ON r.invitation_id = i.invitation_id " +
                "JOIN users u ON r.sender_user_mobile = u.mobile " +
                "WHERE r.recipient_user_mobile = ? " +
                "ORDER BY r.received_timestamp DESC";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userMobile);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ReceivedInvitation rec = new ReceivedInvitation();
                    rec.setReceivedTime(rs.getTimestamp("received_timestamp"));
                    rec.setStatus(rs.getString("status"));
                    rec.setSenderName(rs.getString("sender_name"));
                    rec.setBrideName(rs.getString("bride_name"));
                    rec.setGroomName(rs.getString("groom_name"));
                    rec.setEventDate(rs.getString("event_date"));
                    rec.setEventTime(rs.getString("event_time"));
                    rec.setVenue(rs.getString("venue"));
                    rec.setAddress(rs.getString("address"));
                    rec.setDressCode(rs.getString("dress_code"));
                    rec.setInvitationId(rs.getInt("invitation_id"));
                    receivedList.add(rec);
                }
            }
        }
        return receivedList;
    }

    // Methods for recording sent and received invitations
    public boolean recordSentInvitation(int invitationId, int guestId, String hostMobile) throws SQLException {
        String sql = "INSERT INTO sent_invitations (invitation_id, guest_id, user_mobile, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, invitationId);
            pstmt.setInt(2, guestId);
            pstmt.setString(3, hostMobile);
            pstmt.setString(4, "Sent");
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean recordReceivedInvitation(String guestMobile, int invitationId, String hostMobile)
            throws SQLException {
        String sql = "INSERT INTO received_invitations (recipient_user_mobile, invitation_id, sender_user_mobile, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, guestMobile);
            pstmt.setInt(2, invitationId);
            pstmt.setString(3, hostMobile);
            pstmt.setString(4, "New");
            return pstmt.executeUpdate() > 0;
        }
    }
}
