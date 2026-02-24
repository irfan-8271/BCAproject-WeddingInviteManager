package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuestDialog extends JDialog {
    private JTextField nameField;
    private JTextField mobileField;
    private JCheckBox familyCheckBox;
    private JButton saveButton;

    public GuestDialog(JFrame parent, String title, String buttonText) {
        super(parent, title, true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField(15);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Mobile:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mobileField = new JTextField(15);
        add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        familyCheckBox = new JCheckBox("With Family");
        add(familyCheckBox, gbc);

        gbc.gridy = 3;
        saveButton = new JButton(buttonText);
        add(saveButton, gbc);
    }

    public String getGuestName() {
        return nameField.getText().trim();
    }

    public void setGuestName(String name) {
        nameField.setText(name);
    }

    public String getGuestMobile() {
        return mobileField.getText().trim();
    }

    public void setGuestMobile(String mobile) {
        mobileField.setText(mobile);
    }

    public boolean isWithFamily() {
        return familyCheckBox.isSelected();
    }

    public void setWithFamily(boolean withFamily) {
        familyCheckBox.setSelected(withFamily);
    }

    public void setSaveListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
}
