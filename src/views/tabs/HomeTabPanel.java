package views.tabs;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HomeTabPanel extends JPanel {
    private JTextArea inviteTextArea;
    private JButton sendInviteButton;
    private JButton logoutButton;

    public HomeTabPanel() {
        setBackground(SystemColor.activeCaption);
        GridBagLayout gbl_hometab = new GridBagLayout();
        gbl_hometab.columnWidths = new int[] { 0, 0, 0, 0 };
        gbl_hometab.rowHeights = new int[] { 0, 0 };
        gbl_hometab.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_hometab.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        setLayout(gbl_hometab);

        inviteTextArea = new JTextArea();
        inviteTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inviteTextArea.setLineWrap(true);
        inviteTextArea.setWrapStyleWord(true);
        inviteTextArea.setEditable(false);
        inviteTextArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(inviteTextArea);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(10, 10, 5, 10);
        gbc_scrollPane.gridwidth = 4;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        gbc_scrollPane.weightx = 1.0;
        gbc_scrollPane.weighty = 1.0;
        add(scrollPane, gbc_scrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.setOpaque(false);

        sendInviteButton = new JButton("Send Invitation(s)");
        sendInviteButton.setToolTipText("Select guests from 'Guest List' then click here");
        buttonPanel.add(sendInviteButton);

        logoutButton = new JButton("Logout");
        buttonPanel.add(logoutButton);

        GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
        gbc_buttonPanel.anchor = GridBagConstraints.EAST;
        gbc_buttonPanel.gridwidth = 4;
        gbc_buttonPanel.insets = new Insets(0, 10, 5, 10);
        gbc_buttonPanel.gridx = 0;
        gbc_buttonPanel.gridy = 1;
        add(buttonPanel, gbc_buttonPanel);
    }

    public void updateTextArea(String text) {
        inviteTextArea.setText(text);
        inviteTextArea.setCaretPosition(0);
    }

    public void setSendInviteListener(ActionListener listener) {
        sendInviteButton.addActionListener(listener);
    }

    public void setLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public JButton getSendInviteButton() {
        return sendInviteButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
