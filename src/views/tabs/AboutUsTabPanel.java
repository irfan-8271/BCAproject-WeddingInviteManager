package views.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AboutUsTabPanel extends JPanel {
    public AboutUsTabPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 250, 240));

        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setMargin(new Insets(20, 20, 20, 20));
        aboutTextArea.setBackground(new Color(255, 250, 240));

        aboutTextArea.setText(
                "Welcome to the Digital Wedding Invitation Application!\n\n" +
                        "This application was developed to simplify the process of creating and managing wedding invitations. "
                        +
                        "It allows users to seamlessly register, create personalized invitation details, manage a comprehensive guest list, "
                        +
                        "and send digital invitations directly through the platform.\n\n" +
                        "Key Features:\n" +
                        "  * Secure User Registration and Authentication\n" +
                        "  * Create and Edit Wedding Event Details\n" +
                        "  * Dynamic Guest List Management\n" +
                        "  * View Sent Invitation History\n" +
                        "  * View Received Invitations (if registered)\n" +
                        "  * Password Recovery via Security Questions\n\n\n" +
                        "Version: 1.0 (Monolithic)\nVersion: 1.5 (MVC Refactored)\n" +
                        "Developed with Java Swing & JDBC. @Author :- IRFAN BCA2025");

        JScrollPane scrollPane = new JScrollPane(aboutTextArea);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }
}
