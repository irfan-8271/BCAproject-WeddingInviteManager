package views.tabs;

import java.awt.Color;
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
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ProfileTabPanel extends JPanel {
    private JTextField profileNameField;
    private JTextField profileMobileField;
    private JSpinner profileAgeSpinner;
    private JRadioButton profileRdbtnMale;
    private JRadioButton profileRdbtnFemale;
    private JComboBox<String> profileSecurityQuestionComboBox;
    private JTextField profileAnswerField;
    private JButton saveProfileButton;
    private JButton changePasswordButton;
    private final ButtonGroup profileButtonGroup = new ButtonGroup();

    public ProfileTabPanel() {
        setBackground(new Color(255, 228, 196));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagLayout gbl_profile = new GridBagLayout();
        gbl_profile.columnWidths = new int[] { 150, 200, 100, 0 };
        gbl_profile.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 40, 0 };
        gbl_profile.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
        gbl_profile.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
        setLayout(gbl_profile);

        Font labelFont = new Font("Tahoma", Font.BOLD, 14);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 14);

        // Name
        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setFont(labelFont);
        add(nameLbl, createGbc(0, 0, GridBagConstraints.EAST));
        profileNameField = new JTextField(20);
        profileNameField.setFont(fieldFont);
        GridBagConstraints gbc_name = createGbc(1, 0, GridBagConstraints.WEST);
        gbc_name.gridwidth = 2;
        add(profileNameField, gbc_name);

        // Mobile
        JLabel mobileLbl = new JLabel("Mobile No:");
        mobileLbl.setFont(labelFont);
        add(mobileLbl, createGbc(0, 1, GridBagConstraints.EAST));
        profileMobileField = new JTextField(20);
        profileMobileField.setFont(fieldFont);
        profileMobileField.setEditable(false);
        profileMobileField.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_mobile = createGbc(1, 1, GridBagConstraints.WEST);
        gbc_mobile.gridwidth = 2;
        add(profileMobileField, gbc_mobile);

        // Age
        JLabel ageLbl = new JLabel("Age:");
        ageLbl.setFont(labelFont);
        add(ageLbl, createGbc(0, 2, GridBagConstraints.EAST));
        profileAgeSpinner = new JSpinner(new SpinnerNumberModel(22, 15, 100, 1));
        profileAgeSpinner.setFont(fieldFont);
        add(profileAgeSpinner, createGbc(1, 2, GridBagConstraints.WEST));

        // Gender
        JLabel genderLbl = new JLabel("Gender:");
        genderLbl.setFont(labelFont);
        add(genderLbl, createGbc(0, 3, GridBagConstraints.EAST));
        profileRdbtnMale = new JRadioButton("Male");
        profileRdbtnMale.setOpaque(false);
        profileButtonGroup.add(profileRdbtnMale);
        add(profileRdbtnMale, createGbc(1, 3, GridBagConstraints.WEST));
        profileRdbtnFemale = new JRadioButton("Female");
        profileRdbtnFemale.setOpaque(false);
        profileButtonGroup.add(profileRdbtnFemale);
        add(profileRdbtnFemale, createGbc(2, 3, GridBagConstraints.WEST));

        // Security Question
        JLabel seqQuestLbl = new JLabel("Security Question:");
        seqQuestLbl.setFont(labelFont);
        add(seqQuestLbl, createGbc(0, 4, GridBagConstraints.EAST));
        profileSecurityQuestionComboBox = new JComboBox<>();
        profileSecurityQuestionComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "Your Pet Name ??", "Historical Hero that Inspires You ??",
                "Your Childhood Name ??", "Your Favorite Book ??" }));
        profileSecurityQuestionComboBox.setFont(fieldFont);
        GridBagConstraints gbc_combo = createGbc(1, 4, GridBagConstraints.WEST);
        gbc_combo.gridwidth = 2;
        add(profileSecurityQuestionComboBox, gbc_combo);

        // Security Answer
        JLabel seqAnsLbl = new JLabel("Answer:");
        seqAnsLbl.setFont(labelFont);
        add(seqAnsLbl, createGbc(0, 5, GridBagConstraints.EAST));
        profileAnswerField = new JTextField(20);
        profileAnswerField.setFont(fieldFont);
        GridBagConstraints gbc_ans = createGbc(1, 5, GridBagConstraints.WEST);
        gbc_ans.gridwidth = 2;
        add(profileAnswerField, gbc_ans);

        // Buttons
        saveProfileButton = new JButton("Update Profile");
        saveProfileButton.setFont(labelFont);
        GridBagConstraints gbc_saveBtn = createGbc(1, 6, GridBagConstraints.EAST);
        gbc_saveBtn.insets.top = 20;
        add(saveProfileButton, gbc_saveBtn);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(labelFont);
        GridBagConstraints gbc_passBtn = createGbc(2, 6, GridBagConstraints.WEST);
        gbc_passBtn.insets.top = 20;
        add(changePasswordButton, gbc_passBtn);
    }

    private GridBagConstraints createGbc(int x, int y, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

    public void setSaveProfileListener(ActionListener listener) {
        saveProfileButton.addActionListener(listener);
    }

    public void setChangePasswordListener(ActionListener listener) {
        changePasswordButton.addActionListener(listener);
    }

    public String getNameInput() {
        return profileNameField.getText().trim();
    }

    public void setNameInput(String name) {
        profileNameField.setText(name);
    }

    public String getMobileInput() {
        return profileMobileField.getText().trim();
    }

    public void setMobileInput(String mobile) {
        profileMobileField.setText(mobile);
    }

    public int getAgeInput() {
        return (Integer) profileAgeSpinner.getValue();
    }

    public void setAgeInput(int age) {
        profileAgeSpinner.setValue(age);
    }

    public String getGenderInput() {
        return profileRdbtnMale.isSelected() ? "Male" : "Female";
    }

    public void setGenderInput(String gender) {
        if ("Female".equalsIgnoreCase(gender)) {
            profileRdbtnFemale.setSelected(true);
        } else {
            profileRdbtnMale.setSelected(true);
        }
    }

    public String getSecurityQuestionInput() {
        return (String) profileSecurityQuestionComboBox.getSelectedItem();
    }

    public void setSecurityQuestionInput(String question) {
        profileSecurityQuestionComboBox.setSelectedItem(question);
    }

    public String getSecurityAnswerInput() {
        return profileAnswerField.getText().trim();
    }

    public void setSecurityAnswerInput(String answer) {
        profileAnswerField.setText(answer);
    }
}
