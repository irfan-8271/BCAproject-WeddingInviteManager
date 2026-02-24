package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class SignUpPanel extends JSplitPane {
    private JTextField signUpNameField;
    private JTextField signUpMobileField;
    private JSpinner signUpAgeSpinner;
    private JRadioButton signUpRdbtnMale, signUpRdbtnFemale;
    private JComboBox<String> signUpSecurityQuestionComboBox;
    private JTextField signUpAnswerField;
    private JPasswordField signUpPassField;
    private JButton signBtn;
    private JButton toLoginBtn;
    private final ButtonGroup signUpButtonGroup = new ButtonGroup();

    public SignUpPanel() {
        setDividerLocation(300);
        setDividerSize(5);

        // Left Panel
        ImagePanel leftPanel = new ImagePanel("icons/left.jpg");
        leftPanel.setMinimumSize(new Dimension(405, 200));
        leftPanel.setLayout(new BorderLayout());
        setLeftComponent(leftPanel);

        // Right Panel
        JPanel signpanel = new JPanel();
        signpanel.setBackground(new Color(234, 154, 71));
        GridBagLayout gbl_signpanel = new GridBagLayout();
        gbl_signpanel.columnWidths = new int[] { 100, 100, 100, 100, 100 };
        gbl_signpanel.rowHeights = new int[] { 0, 0, 0, 0, 20, 20, 0, 0, 0, 0, 20, 20, 20, 20, 0, 0, 0, 20 };
        gbl_signpanel.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0 };
        gbl_signpanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 1.0 };
        signpanel.setLayout(gbl_signpanel);
        setRightComponent(signpanel);

        JLabel title = new JLabel("SIGN UP FORM");
        title.setVerticalAlignment(SwingConstants.TOP);
        title.setToolTipText("Fill The Form");
        title.setFont(new Font("Algerian", Font.BOLD, 20));
        GridBagConstraints gbc_title = new GridBagConstraints();
        gbc_title.anchor = GridBagConstraints.NORTH;
        gbc_title.gridwidth = 2;
        gbc_title.insets = new Insets(10, 0, 15, 5);
        gbc_title.gridx = 1;
        gbc_title.gridy = 0;
        signpanel.add(title, gbc_title);

        ImagePanel addicon = new ImagePanel("icons/Add.jpg");
        GridBagConstraints gbc_addicon = new GridBagConstraints();
        gbc_addicon.gridheight = 6;
        gbc_addicon.insets = new Insets(10, 0, 5, 5);
        gbc_addicon.gridwidth = 2;
        gbc_addicon.gridx = 3;
        gbc_addicon.gridy = 0;
        gbc_addicon.fill = GridBagConstraints.BOTH;
        gbc_addicon.weightx = 0.4;
        gbc_addicon.weighty = 0.5;
        signpanel.add(addicon, gbc_addicon);

        // Name
        JLabel namelbl = new JLabel("NAME-: ");
        namelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_namelbl = new GridBagConstraints();
        gbc_namelbl.anchor = GridBagConstraints.EAST;
        gbc_namelbl.insets = new Insets(5, 5, 5, 5);
        gbc_namelbl.gridx = 0;
        gbc_namelbl.gridy = 2;
        signpanel.add(namelbl, gbc_namelbl);

        signUpNameField = new JTextField();
        signUpNameField.setToolTipText("Enter your Name");
        GridBagConstraints gbc_nameField = new GridBagConstraints();
        gbc_nameField.gridwidth = 2;
        gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
        gbc_nameField.insets = new Insets(5, 0, 5, 5);
        gbc_nameField.gridx = 1;
        gbc_nameField.gridy = 2;
        gbc_nameField.weightx = 0.6;
        signpanel.add(signUpNameField, gbc_nameField);

        // Mobile
        JLabel mobilelbl = new JLabel("Mobile No-:");
        mobilelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_mobilelbl = new GridBagConstraints();
        gbc_mobilelbl.anchor = GridBagConstraints.EAST;
        gbc_mobilelbl.insets = new Insets(5, 5, 5, 5);
        gbc_mobilelbl.gridx = 0;
        gbc_mobilelbl.gridy = 4;
        signpanel.add(mobilelbl, gbc_mobilelbl);

        signUpMobileField = new JTextField();
        signUpMobileField.setToolTipText("Enter Your 10-digit Mobile No");
        GridBagConstraints gbc_mobileField = new GridBagConstraints();
        gbc_mobileField.gridwidth = 2;
        gbc_mobileField.insets = new Insets(5, 0, 5, 5);
        gbc_mobileField.fill = GridBagConstraints.HORIZONTAL;
        gbc_mobileField.gridx = 1;
        gbc_mobileField.gridy = 4;
        gbc_mobileField.weightx = 0.6;
        signpanel.add(signUpMobileField, gbc_mobileField);

        // Age
        JLabel agelbl = new JLabel("Age:- ");
        agelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_agelbl = new GridBagConstraints();
        gbc_agelbl.anchor = GridBagConstraints.EAST;
        gbc_agelbl.insets = new Insets(5, 5, 5, 5);
        gbc_agelbl.gridx = 0;
        gbc_agelbl.gridy = 6;
        signpanel.add(agelbl, gbc_agelbl);

        signUpAgeSpinner = new JSpinner();
        signUpAgeSpinner.setModel(new SpinnerNumberModel(22, 15, 75, 1));
        signUpAgeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_spinner = new GridBagConstraints();
        gbc_spinner.anchor = GridBagConstraints.WEST;
        gbc_spinner.insets = new Insets(5, 0, 5, 5);
        gbc_spinner.gridx = 1;
        gbc_spinner.gridy = 6;
        signpanel.add(signUpAgeSpinner, gbc_spinner);

        // Gender
        JLabel genderLabel = new JLabel("Gender-: ");
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_genderLabel = new GridBagConstraints();
        gbc_genderLabel.anchor = GridBagConstraints.EAST;
        gbc_genderLabel.insets = new Insets(5, 5, 5, 5);
        gbc_genderLabel.gridx = 0;
        gbc_genderLabel.gridy = 8;
        signpanel.add(genderLabel, gbc_genderLabel);

        signUpRdbtnMale = new JRadioButton("Male");
        signUpButtonGroup.add(signUpRdbtnMale);
        GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
        gbc_rdbtnMale.anchor = GridBagConstraints.WEST;
        gbc_rdbtnMale.insets = new Insets(5, 0, 5, 5);
        gbc_rdbtnMale.gridx = 1;
        gbc_rdbtnMale.gridy = 8;
        signpanel.add(signUpRdbtnMale, gbc_rdbtnMale);
        signUpRdbtnMale.setSelected(true);

        signUpRdbtnFemale = new JRadioButton("Female");
        signUpButtonGroup.add(signUpRdbtnFemale);
        GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
        gbc_rdbtnFemale.anchor = GridBagConstraints.WEST;
        gbc_rdbtnFemale.insets = new Insets(5, 0, 5, 5);
        gbc_rdbtnFemale.gridx = 2;
        gbc_rdbtnFemale.gridy = 8;
        signpanel.add(signUpRdbtnFemale, gbc_rdbtnFemale);

        // Password
        JLabel passlbl = new JLabel("Password-: ");
        passlbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_passlbl = new GridBagConstraints();
        gbc_passlbl.anchor = GridBagConstraints.EAST;
        gbc_passlbl.insets = new Insets(5, 5, 5, 5);
        gbc_passlbl.gridx = 0;
        gbc_passlbl.gridy = 10;
        signpanel.add(passlbl, gbc_passlbl);

        signUpPassField = new JPasswordField();
        signUpPassField.setEchoChar('*');
        signUpPassField.setToolTipText("Enter a password (min 6 chars recommended)");
        GridBagConstraints gbc_passField = new GridBagConstraints();
        gbc_passField.gridwidth = 2;
        gbc_passField.insets = new Insets(5, 0, 5, 5);
        gbc_passField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passField.gridx = 1;
        gbc_passField.gridy = 10;
        gbc_passField.weightx = 0.6;
        signpanel.add(signUpPassField, gbc_passField);

        // Security Question
        JLabel questionLbl = new JLabel("Security Question -:");
        questionLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_questionLbl = new GridBagConstraints();
        gbc_questionLbl.anchor = GridBagConstraints.EAST;
        gbc_questionLbl.insets = new Insets(5, 5, 5, 5);
        gbc_questionLbl.gridx = 0;
        gbc_questionLbl.gridy = 12;
        signpanel.add(questionLbl, gbc_questionLbl);

        signUpSecurityQuestionComboBox = new JComboBox<>();
        signUpSecurityQuestionComboBox.setToolTipText("Choose a question for password recovery.");
        signUpSecurityQuestionComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "Your Pet Name ??", "Historical Hero that Inspires You ??",
                "Your Childhood Name ??", "Your Favorite Book ??" }));
        signUpSecurityQuestionComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 2;
        gbc_comboBox.insets = new Insets(5, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 12;
        gbc_comboBox.weightx = 0.6;
        signpanel.add(signUpSecurityQuestionComboBox, gbc_comboBox);

        // Answer
        JLabel answerlbl = new JLabel("Answer-: ");
        answerlbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GridBagConstraints gbc_answerlbl = new GridBagConstraints();
        gbc_answerlbl.anchor = GridBagConstraints.EAST;
        gbc_answerlbl.insets = new Insets(5, 5, 5, 5);
        gbc_answerlbl.gridx = 0;
        gbc_answerlbl.gridy = 14;
        signpanel.add(answerlbl, gbc_answerlbl);

        signUpAnswerField = new JTextField();
        signUpAnswerField.setToolTipText("Enter the answer to your security question");
        GridBagConstraints gbc_answerField = new GridBagConstraints();
        gbc_answerField.gridwidth = 2;
        gbc_answerField.insets = new Insets(5, 0, 5, 5);
        gbc_answerField.fill = GridBagConstraints.HORIZONTAL;
        gbc_answerField.gridx = 1;
        gbc_answerField.gridy = 14;
        gbc_answerField.weightx = 0.6;
        signpanel.add(signUpAnswerField, gbc_answerField);

        // Sign Up Button
        signBtn = new JButton("Sign up");
        signBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_Signbtn = new GridBagConstraints();
        gbc_Signbtn.anchor = GridBagConstraints.CENTER;
        gbc_Signbtn.insets = new Insets(15, 0, 5, 5);
        gbc_Signbtn.gridx = 1;
        gbc_Signbtn.gridy = 16;
        signpanel.add(signBtn, gbc_Signbtn);

        toLoginBtn = new JButton("Already have an account? Login");
        GridBagConstraints gbc_tologinBtn = new GridBagConstraints();
        gbc_tologinBtn.anchor = GridBagConstraints.EAST;
        gbc_tologinBtn.insets = new Insets(15, 0, 5, 5);
        gbc_tologinBtn.gridx = 3;
        gbc_tologinBtn.gridwidth = 2;
        gbc_tologinBtn.gridy = 16;
        signpanel.add(toLoginBtn, gbc_tologinBtn);
    }

    public void clearFields() {
        signUpNameField.setText("");
        signUpMobileField.setText("");
        signUpPassField.setText("");
        signUpAnswerField.setText("");
        signUpAgeSpinner.setValue(22);
        signUpRdbtnMale.setSelected(true);
        signUpSecurityQuestionComboBox.setSelectedIndex(0);
    }

    // Getters for fields
    public String getNameInput() {
        return signUpNameField.getText().trim();
    }

    public String getMobileInput() {
        return signUpMobileField.getText().trim();
    }

    public String getPasswordInput() {
        return new String(signUpPassField.getPassword());
    }

    public String getAnswerInput() {
        return signUpAnswerField.getText().trim();
    }

    public int getAgeInput() {
        return (Integer) signUpAgeSpinner.getValue();
    }

    public String getGenderInput() {
        return signUpRdbtnMale.isSelected() ? "Male" : "Female";
    }

    public String getSecurityQuestionInput() {
        return (String) signUpSecurityQuestionComboBox.getSelectedItem();
    }

    public void setSignUpListener(ActionListener listener) {
        signBtn.addActionListener(listener);
    }

    public void setToLoginListener(ActionListener listener) {
        toLoginBtn.addActionListener(listener);
    }

    public JButton getSignBtn() {
        return signBtn;
    }
}
