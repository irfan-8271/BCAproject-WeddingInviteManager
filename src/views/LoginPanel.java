package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton goToForgotPasswordButton;
    private JButton goToSignUpButton;

    public LoginPanel() {
        setBackground(new Color(150, 203, 201));
        GridBagLayout gbl_loginpanel = new GridBagLayout();
        gbl_loginpanel.columnWidths = new int[] { 0, 0, 20, 0, 0, 0, 0, 0, 20, 20, 20, 20, 20 };
        gbl_loginpanel.rowHeights = new int[] { 15, 15, 0, 0, 0, 0, 0, 0, 0, 15, 15, 15, 15 };
        gbl_loginpanel.columnWeights = new double[] { 0.4, 0.0, 0.0, 0.0, 0.6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        gbl_loginpanel.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.0, 0.1, 0.0, 0.0, 0.1, 0.4, 0.0, 0.0, 0.0, 0.0 };
        setLayout(gbl_loginpanel);

        ImagePanel addicon = new ImagePanel("icons/Login.jpg");
        GridBagConstraints gbc_addicon = new GridBagConstraints();
        gbc_addicon.gridheight = 9;
        gbc_addicon.insets = new Insets(5, 5, 5, 15);
        gbc_addicon.gridwidth = 2;
        gbc_addicon.gridx = 0;
        gbc_addicon.gridy = 0;
        gbc_addicon.fill = GridBagConstraints.BOTH;
        gbc_addicon.weightx = 0.4;
        gbc_addicon.weighty = 1.0;
        add(addicon, gbc_addicon);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Algerian", Font.BOLD, 24));
        GridBagConstraints gbc_titleLabel = new GridBagConstraints();
        gbc_titleLabel.anchor = GridBagConstraints.CENTER;
        gbc_titleLabel.gridwidth = 2;
        gbc_titleLabel.insets = new Insets(10, 0, 15, 5);
        gbc_titleLabel.gridx = 3;
        gbc_titleLabel.gridy = 0;
        add(titleLabel, gbc_titleLabel);

        JLabel mobilelbl = new JLabel("Mobile-: ");
        mobilelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_mobilelbl = new GridBagConstraints();
        gbc_mobilelbl.anchor = GridBagConstraints.EAST;
        gbc_mobilelbl.insets = new Insets(10, 10, 5, 5);
        gbc_mobilelbl.gridx = 3;
        gbc_mobilelbl.gridy = 1;
        add(mobilelbl, gbc_mobilelbl);

        usernameField = new JTextField(15);
        usernameField.setToolTipText("Enter your registered Mobile Number");
        GridBagConstraints gbc_usernameField = new GridBagConstraints();
        gbc_usernameField.anchor = GridBagConstraints.WEST;
        gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
        gbc_usernameField.insets = new Insets(10, 0, 5, 5);
        gbc_usernameField.gridx = 4;
        gbc_usernameField.gridy = 1;
        gbc_usernameField.weightx = 0.6;
        add(usernameField, gbc_usernameField);

        JLabel label = new JLabel("Password:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.EAST;
        gbc_label.insets = new Insets(5, 10, 5, 5);
        gbc_label.gridx = 3;
        gbc_label.gridy = 2;
        add(label, gbc_label);

        passwordField = new JPasswordField(15);
        passwordField.setToolTipText("Enter your password");
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.anchor = GridBagConstraints.WEST;
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.insets = new Insets(5, 0, 5, 5);
        gbc_passwordField.gridx = 4;
        gbc_passwordField.gridy = 2;
        gbc_passwordField.weightx = 0.6;
        add(passwordField, gbc_passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setOpaque(false);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonPanel.add(loginButton);

        goToForgotPasswordButton = new JButton("Forgot Password?");
        goToForgotPasswordButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        buttonPanel.add(goToForgotPasswordButton);

        GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
        gbc_buttonPanel.gridwidth = 2;
        gbc_buttonPanel.gridx = 3;
        gbc_buttonPanel.gridy = 4;
        gbc_buttonPanel.insets = new Insets(10, 0, 5, 5);
        gbc_buttonPanel.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc_buttonPanel);

        goToSignUpButton = new JButton("Create an account? Sign Up");
        goToSignUpButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_goToSignUpButton = new GridBagConstraints();
        gbc_goToSignUpButton.gridwidth = 2;
        gbc_goToSignUpButton.anchor = GridBagConstraints.CENTER;
        gbc_goToSignUpButton.insets = new Insets(5, 0, 5, 5);
        gbc_goToSignUpButton.gridx = 3;
        gbc_goToSignUpButton.gridy = 7;
        add(goToSignUpButton, gbc_goToSignUpButton);
    }

    public String getMobile() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void setLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setForgotPasswordListener(ActionListener listener) {
        goToForgotPasswordButton.addActionListener(listener);
    }

    public void setSignUpListener(ActionListener listener) {
        goToSignUpButton.addActionListener(listener);
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
