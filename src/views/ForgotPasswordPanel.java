package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ForgotPasswordPanel extends JPanel {
    private JTextField forgotMobileField;
    private JTextField forgotNameField;
    private JTextField forgotQuestionField;
    private JTextField forgotAnswerField;
    private JTextField forgotPasswordField;

    private JButton searchbtn;
    private JButton verifybtn;
    private JButton goToLoginButton;

    public ForgotPasswordPanel() {
        setBackground(new Color(0, 153, 255));
        GridBagLayout gbl_pswdRecoverpanel = new GridBagLayout();
        setLayout(gbl_pswdRecoverpanel);
        gbl_pswdRecoverpanel.columnWidths = new int[] { 120, 180, 100, 100, 100, 100 };
        gbl_pswdRecoverpanel.rowHeights = new int[] { 0, 30, 30, 30, 30, 30, 30, 30, 0 };
        gbl_pswdRecoverpanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_pswdRecoverpanel.rowWeights = new double[] { 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 1.0 };

        JLabel titleLabel = new JLabel("Password Recovery");
        titleLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        GridBagConstraints gbc_titleLabel = new GridBagConstraints();
        gbc_titleLabel.gridwidth = 3;
        gbc_titleLabel.anchor = GridBagConstraints.CENTER;
        gbc_titleLabel.insets = new Insets(10, 0, 15, 5);
        gbc_titleLabel.gridx = 0;
        gbc_titleLabel.gridy = 0;
        add(titleLabel, gbc_titleLabel);

        JLabel moblbl = new JLabel("Mobile:-");
        moblbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        moblbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_moblbl = new GridBagConstraints();
        gbc_moblbl.anchor = GridBagConstraints.EAST;
        gbc_moblbl.insets = new Insets(0, 5, 5, 5);
        gbc_moblbl.gridx = 0;
        gbc_moblbl.gridy = 1;
        add(moblbl, gbc_moblbl);

        forgotMobileField = new JTextField(15);
        forgotMobileField.setToolTipText("Enter your registered Mobile Number");
        GridBagConstraints gbc_mobileField_forgot = new GridBagConstraints();
        gbc_mobileField_forgot.anchor = GridBagConstraints.WEST;
        gbc_mobileField_forgot.fill = GridBagConstraints.HORIZONTAL;
        gbc_mobileField_forgot.insets = new Insets(0, 5, 5, 5);
        gbc_mobileField_forgot.gridx = 1;
        gbc_mobileField_forgot.gridy = 1;
        add(forgotMobileField, gbc_mobileField_forgot);

        searchbtn = new JButton("Search");
        searchbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_searchbtn = new GridBagConstraints();
        gbc_searchbtn.anchor = GridBagConstraints.WEST;
        gbc_searchbtn.insets = new Insets(0, 0, 5, 5);
        gbc_searchbtn.gridx = 2;
        gbc_searchbtn.gridy = 1;
        add(searchbtn, gbc_searchbtn);

        JLabel namelbl = new JLabel("Name:-");
        namelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        namelbl.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_namelbl = new GridBagConstraints();
        gbc_namelbl.anchor = GridBagConstraints.EAST;
        gbc_namelbl.insets = new Insets(0, 0, 5, 5);
        gbc_namelbl.gridx = 0;
        gbc_namelbl.gridy = 2;
        add(namelbl, gbc_namelbl);

        forgotNameField = new JTextField();
        forgotNameField.setEditable(false);
        forgotNameField.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_namefield = new GridBagConstraints();
        gbc_namefield.anchor = GridBagConstraints.WEST;
        gbc_namefield.fill = GridBagConstraints.HORIZONTAL;
        gbc_namefield.insets = new Insets(0, 5, 5, 5);
        gbc_namefield.gridx = 1;
        gbc_namefield.gridy = 2;
        add(forgotNameField, gbc_namefield);

        JLabel question = new JLabel("Your Security Question:-");
        question.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_question = new GridBagConstraints();
        gbc_question.anchor = GridBagConstraints.EAST;
        gbc_question.insets = new Insets(0, 0, 5, 5);
        gbc_question.gridx = 0;
        gbc_question.gridy = 3;
        add(question, gbc_question);

        forgotQuestionField = new JTextField();
        forgotQuestionField.setEditable(false);
        forgotQuestionField.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_questionfield = new GridBagConstraints();
        gbc_questionfield.anchor = GridBagConstraints.WEST;
        gbc_questionfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_questionfield.insets = new Insets(0, 5, 5, 5);
        gbc_questionfield.gridx = 1;
        gbc_questionfield.gridy = 3;
        gbc_questionfield.gridwidth = 2;
        add(forgotQuestionField, gbc_questionfield);

        JLabel anslbl = new JLabel("Answer:-");
        anslbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_anslbl = new GridBagConstraints();
        gbc_anslbl.anchor = GridBagConstraints.EAST;
        gbc_anslbl.insets = new Insets(0, 0, 5, 5);
        gbc_anslbl.gridx = 0;
        gbc_anslbl.gridy = 4;
        add(anslbl, gbc_anslbl);

        forgotAnswerField = new JTextField();
        forgotAnswerField.setToolTipText("Enter the answer to your security question");
        GridBagConstraints gbc_ansfield = new GridBagConstraints();
        gbc_ansfield.anchor = GridBagConstraints.WEST;
        gbc_ansfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_ansfield.insets = new Insets(0, 5, 5, 5);
        gbc_ansfield.gridx = 1;
        gbc_ansfield.gridy = 4;
        add(forgotAnswerField, gbc_ansfield);

        ImagePanel addicon = new ImagePanel("icons/forgot.png");
        addicon.setBackground(new Color(0, 153, 255));
        GridBagConstraints gbc_addicon_f = new GridBagConstraints();
        gbc_addicon_f.gridheight = 7;
        gbc_addicon_f.insets = new Insets(0, 10, 5, 5);
        gbc_addicon_f.gridwidth = 3;
        gbc_addicon_f.gridx = 3;
        gbc_addicon_f.gridy = 0;
        gbc_addicon_f.fill = GridBagConstraints.BOTH;
        gbc_addicon_f.weightx = 0.5;
        gbc_addicon_f.weighty = 1.0;
        add(addicon, gbc_addicon_f);

        JLabel passslbl = new JLabel("Password:-");
        passslbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_passslbl = new GridBagConstraints();
        gbc_passslbl.anchor = GridBagConstraints.EAST;
        gbc_passslbl.insets = new Insets(0, 0, 5, 5);
        gbc_passslbl.gridx = 0;
        gbc_passslbl.gridy = 5;
        add(passslbl, gbc_passslbl);

        forgotPasswordField = new JTextField();
        forgotPasswordField.setEditable(false);
        forgotPasswordField.setBackground(Color.LIGHT_GRAY);
        forgotPasswordField.setFont(new Font("Tahoma", Font.BOLD, 14));

        GridBagConstraints gbc_pswdfield = new GridBagConstraints();
        gbc_pswdfield.anchor = GridBagConstraints.WEST;
        gbc_pswdfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_pswdfield.insets = new Insets(0, 5, 5, 5);
        gbc_pswdfield.gridx = 1;
        gbc_pswdfield.gridy = 5;
        add(forgotPasswordField, gbc_pswdfield);

        verifybtn = new JButton("Verify & Retrieve");
        verifybtn.setFont(new Font("Tahoma", Font.ITALIC, 15));
        GridBagConstraints gbc_verifybtn = new GridBagConstraints();
        gbc_verifybtn.anchor = GridBagConstraints.WEST;
        gbc_verifybtn.insets = new Insets(0, 0, 5, 5);
        gbc_verifybtn.gridx = 2;
        gbc_verifybtn.gridy = 5;
        add(verifybtn, gbc_verifybtn);

        goToLoginButton = new JButton("Back to Login");
        goToLoginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_goToLoginButton = new GridBagConstraints();
        gbc_goToLoginButton.insets = new Insets(20, 10, 10, 10);
        gbc_goToLoginButton.gridx = 1;
        gbc_goToLoginButton.gridy = 7;
        gbc_goToLoginButton.anchor = GridBagConstraints.CENTER;
        add(goToLoginButton, gbc_goToLoginButton);
    }

    public void clearFields() {
        forgotMobileField.setText("");
        forgotNameField.setText("");
        forgotQuestionField.setText("");
        forgotAnswerField.setText("");
        forgotPasswordField.setText("");
    }

    public String getMobileInput() {
        return forgotMobileField.getText().trim();
    }

    public String getAnswerInput() {
        return forgotAnswerField.getText().trim();
    }

    public String getQuestionFieldText() {
        return forgotQuestionField.getText();
    }

    public void setNameField(String name) {
        forgotNameField.setText(name);
    }

    public void setQuestionField(String question) {
        forgotQuestionField.setText(question);
    }

    public void setPasswordField(String password) {
        forgotPasswordField.setText(password);
    }

    public void clearPassword() {
        forgotPasswordField.setText("");
    }

    public void clearAnswer() {
        forgotAnswerField.setText("");
    }

    public void setSearchListener(ActionListener listener) {
        searchbtn.addActionListener(listener);
    }

    public void setVerifyListener(ActionListener listener) {
        verifybtn.addActionListener(listener);
    }

    public void setToLoginListener(ActionListener listener) {
        goToLoginButton.addActionListener(listener);
    }

    public JButton getSearchBtn() {
        return searchbtn;
    }

    public JButton getVerifyBtn() {
        return verifybtn;
    }
}
