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

public class SentHistoryTabPanel extends JPanel {
    private JPanel sentHistoryPanelContainer;

    public SentHistoryTabPanel() {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(0, 128, 128)); // Teal color
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        sentHistoryPanelContainer = new JPanel();
        sentHistoryPanelContainer.setLayout(new BoxLayout(sentHistoryPanelContainer, BoxLayout.Y_AXIS));
        sentHistoryPanelContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(sentHistoryPanelContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void clearHistory() {
        sentHistoryPanelContainer.removeAll();
    }

    public void showNoHistoryMessage() {
        JLabel noHistoryLabel = new JLabel("  You have not sent any invitations yet.  ");
        noHistoryLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        noHistoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sentHistoryPanelContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        sentHistoryPanelContainer.add(noHistoryLabel);
        sentHistoryPanelContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        refreshPanel();
    }

    public void showErrorMessage(String msg) {
        JLabel errorLabel = new JLabel("  Error: " + msg + "  ");
        errorLabel.setForeground(Color.RED);
        sentHistoryPanelContainer.add(errorLabel);
        refreshPanel();
    }

    public void addHistoryEntry(Timestamp sentTime, String guestName, String guestMobile, String bride, String groom,
            String date) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String text = String.format("<html><b>Sent:</b> %s | <b>To:</b> %s (%s) | <b>Event:</b> %s & %s on %s</html>",
                sentTime != null ? sentTime.toString().substring(0, 16) : "N/A",
                guestName != null ? guestName : "N/A",
                guestMobile != null ? guestMobile : "N/A",
                bride != null ? bride : "[Bride]",
                groom != null ? groom : "[Groom]",
                date != null ? date : "[Date]");

        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(label);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height + 5));

        sentHistoryPanelContainer.add(panel);
        sentHistoryPanelContainer.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public void refreshPanel() {
        sentHistoryPanelContainer.revalidate();
        sentHistoryPanelContainer.repaint();
    }
}
