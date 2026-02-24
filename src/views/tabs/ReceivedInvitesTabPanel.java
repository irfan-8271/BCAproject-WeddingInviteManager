package views.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Timestamp;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ReceivedInvitesTabPanel extends JPanel {
    private JPanel receivedInvitesPanelContainer;

    public ReceivedInvitesTabPanel() {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(200, 230, 200)); // Light green-ish
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        receivedInvitesPanelContainer = new JPanel();
        receivedInvitesPanelContainer.setLayout(new BoxLayout(receivedInvitesPanelContainer, BoxLayout.Y_AXIS));
        receivedInvitesPanelContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(receivedInvitesPanelContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void clearInvites() {
        receivedInvitesPanelContainer.removeAll();
    }

    public void showNoInvitesMessage() {
        JLabel noHistoryLabel = new JLabel("  You have not received any invitations yet.  ");
        noHistoryLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        noHistoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        receivedInvitesPanelContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        receivedInvitesPanelContainer.add(noHistoryLabel);
        receivedInvitesPanelContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        refreshPanel();
    }

    public void showErrorMessage(String msg) {
        JLabel errorLabel = new JLabel("  Error: " + msg + "  ");
        errorLabel.setForeground(Color.RED);
        receivedInvitesPanelContainer.add(errorLabel);
        refreshPanel();
    }

    public void addReceivedInvite(Timestamp receivedTime, String status, String sender,
            String bride, String groom, String date, String time, String venue,
            String address, String dressCode, int invitationId) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        panel.setBackground(new Color(248, 248, 255)); // Ghost white
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Font boldFont = new Font("SansSerif", Font.BOLD, 13);
        Font plainFont = new Font("SansSerif", Font.PLAIN, 12);
        Font smallItalicFont = new Font("SansSerif", Font.ITALIC, 11);

        // Top Section
        JPanel topPanel = new JPanel(new BorderLayout(5, 0));
        topPanel.setOpaque(false);
        JLabel senderLabel = new JLabel("From: " + (sender != null ? sender : "Unknown Sender"));
        senderLabel.setFont(boldFont);
        topPanel.add(senderLabel, BorderLayout.WEST);

        JLabel timeLabel = new JLabel(
                "Received: " + (receivedTime != null ? receivedTime.toString().substring(0, 16) : "N/A"));
        timeLabel.setFont(smallItalicFont);
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(timeLabel, BorderLayout.EAST);
        panel.add(topPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Middle Section
        JLabel eventLabel = new JLabel(String.format("Invites you to the wedding of %s & %s",
                (bride != null ? bride : "[Bride]"), (groom != null ? groom : "[Groom]")));
        eventLabel.setFont(boldFont);
        panel.add(eventLabel);

        panel.add(new JLabel(String.format("Date & Time: %s at %s",
                (date != null ? date : "[Date]"), (time != null ? time : "[Time]")))).setFont(plainFont);
        panel.add(new JLabel(String.format("Venue: %s", (venue != null ? venue : "[Venue]")))).setFont(plainFont);

        if (address != null && !address.trim().isEmpty()) {
            panel.add(new JLabel(String.format("Address: %s", address))).setFont(plainFont);
        }
        if (dressCode != null && !dressCode.trim().isEmpty()) {
            panel.add(new JLabel("Dress Code: " + dressCode)).setFont(plainFont);
        }
        panel.add(Box.createRigidArea(new Dimension(0, 8)));

        // Bottom Section
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        bottomPanel.setOpaque(false);
        JLabel statusLabel = new JLabel("Status: " + (status != null ? status : "Unknown"));
        statusLabel.setFont(boldFont);

        if ("Accepted".equalsIgnoreCase(status))
            statusLabel.setForeground(new Color(0, 128, 0));
        else if ("Declined".equalsIgnoreCase(status))
            statusLabel.setForeground(Color.RED);
        else
            statusLabel.setForeground(Color.BLUE);

        bottomPanel.add(statusLabel);
        panel.add(bottomPanel);

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));

        receivedInvitesPanelContainer.add(panel);
        receivedInvitesPanelContainer.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public void refreshPanel() {
        receivedInvitesPanelContainer.revalidate();
        receivedInvitesPanelContainer.repaint();
    }
}
