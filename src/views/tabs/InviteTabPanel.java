package views.tabs;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InviteTabPanel extends JPanel {
    private JTextField brideNameTextField;
    private JTextField groomNameTextField;
    private JTextField dateTextField;
    private JTextField timeTextField;
    private JTextField venueTextField;
    private JTextField addressTextField;
    private JTextField dressCodeTextField;
    private JButton saveButton;

    public InviteTabPanel() {
        setBackground(SystemColor.activeCaption);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagLayout gbl_createInvite = new GridBagLayout();
        gbl_createInvite.columnWidths = new int[] { 180, 300, 100 };
        gbl_createInvite.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_createInvite.columnWeights = new double[] { 0.0, 1.0, 0.0 };
        gbl_createInvite.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
        setLayout(gbl_createInvite);

        int currentRow = 0;
        Font labelFont = new Font("Tahoma", Font.BOLD, 13);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 13);
        Insets labelInsets = new Insets(5, 5, 5, 10);
        Insets fieldInsets = new Insets(5, 0, 5, 5);

        // Bride's Name
        JLabel brideNamelbl = new JLabel("Bride's Name:");
        brideNamelbl.setFont(labelFont);
        GridBagConstraints gbc_brideNamelbl = createGbc(0, currentRow, GridBagConstraints.EAST, labelInsets);
        add(brideNamelbl, gbc_brideNamelbl);
        brideNameTextField = new JTextField();
        brideNameTextField.setFont(fieldFont);
        GridBagConstraints gbc_brideNameTextField = createGbc(1, currentRow++, GridBagConstraints.WEST, fieldInsets);
        gbc_brideNameTextField.fill = GridBagConstraints.HORIZONTAL;
        add(brideNameTextField, gbc_brideNameTextField);

        // Groom's Name
        JLabel groomNamelbl = new JLabel("Groom's Name:");
        groomNamelbl.setFont(labelFont);
        GridBagConstraints gbc_groomNamelbl = createGbc(0, currentRow, GridBagConstraints.EAST, labelInsets);
        add(groomNamelbl, gbc_groomNamelbl);
        groomNameTextField = new JTextField();
        groomNameTextField.setFont(fieldFont);
        GridBagConstraints gbc_groomNameTextField = createGbc(1, currentRow++, GridBagConstraints.WEST, fieldInsets);
        gbc_groomNameTextField.fill = GridBagConstraints.HORIZONTAL;
        add(groomNameTextField, gbc_groomNameTextField);

        // Date
        JLabel dateLbl = new JLabel("Date (e.g., Sat, Oct 26, 2024):");
        dateLbl.setFont(labelFont);
        GridBagConstraints gbc_dateLbl = createGbc(0, currentRow, GridBagConstraints.EAST, labelInsets);
        add(dateLbl, gbc_dateLbl);
        dateTextField = new JTextField();
        dateTextField.setFont(fieldFont);
        dateTextField.setToolTipText("Format examples: 2024-10-26, October 26, 2024");
        GridBagConstraints gbc_dateTextField = createGbc(1, currentRow++, GridBagConstraints.WEST, fieldInsets);
        gbc_dateTextField.fill = GridBagConstraints.HORIZONTAL;
        add(dateTextField, gbc_dateTextField);

        // Time
        JLabel timeLbl = new JLabel("Time (e.g., 05:00 PM):");
        timeLbl.setFont(labelFont);
        GridBagConstraints gbc_timeLbl = createGbc(0, currentRow, GridBagConstraints.EAST, labelInsets);
        add(timeLbl, gbc_timeLbl);
        timeTextField = new JTextField();
        timeTextField.setFont(fieldFont);
        timeTextField.setToolTipText("Format examples: 5:00 PM, 17:00");
        GridBagConstraints gbc_timeTextField = createGbc(1, currentRow++, GridBagConstraints.WEST, fieldInsets);
        gbc_timeTextField.fill = GridBagConstraints.HORIZONTAL;
        add(timeTextField, gbc_timeTextField);

        // Venue
        JLabel venueLbl = new JLabel("Venue Name:");
        venueLbl.setFont(labelFont);
        GridBagConstraints gbc_venueLbl = createGbc(0, currentRow, GridBagConstraints.EAST, labelInsets);
        add(venueLbl, gbc_venueLbl);
        venueTextField = new JTextField();
        venueTextField.setFont(fieldFont);
        GridBagConstraints gbc_venueTextField = createGbc(1, currentRow++, GridBagConstraints.WEST, fieldInsets);
        gbc_venueTextField.fill = GridBagConstraints.HORIZONTAL;
        add(venueTextField, gbc_venueTextField);

        // Address
        JLabel addressLbl = new JLabel("Venue Address:");
        addressLbl.setFont(labelFont);
        GridBagConstraints gbc_addressLbl = createGbc(0, currentRow, GridBagConstraints.EAST, labelInsets);
        add(addressLbl, gbc_addressLbl);
        addressTextField = new JTextField();
        addressTextField.setFont(fieldFont);
        GridBagConstraints gbc_addressTextField = createGbc(1, currentRow++, GridBagConstraints.WEST, fieldInsets);
        gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
        add(addressTextField, gbc_addressTextField);

        // Dress Code
        JLabel dressCodeLbl = new JLabel("Dress Code (Optional):");
        dressCodeLbl.setFont(labelFont);
        GridBagConstraints gbc_dressCodeLbl = createGbc(0, currentRow, GridBagConstraints.EAST, labelInsets);
        add(dressCodeLbl, gbc_dressCodeLbl);
        dressCodeTextField = new JTextField();
        dressCodeTextField.setFont(fieldFont);
        GridBagConstraints gbc_dressCodeTextField = createGbc(1, currentRow++, GridBagConstraints.WEST, fieldInsets);
        gbc_dressCodeTextField.fill = GridBagConstraints.HORIZONTAL;
        add(dressCodeTextField, gbc_dressCodeTextField);

        // Save Button
        saveButton = new JButton("Save Invite Details");
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        GridBagConstraints gbc_saveButton = createGbc(1, currentRow, GridBagConstraints.EAST, new Insets(20, 0, 5, 5));
        add(saveButton, gbc_saveButton);
    }

    private GridBagConstraints createGbc(int x, int y, int anchor, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        gbc.insets = insets;
        return gbc;
    }

    public void setSaveListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public String getBrideName() {
        return brideNameTextField.getText().trim();
    }

    public String getGroomName() {
        return groomNameTextField.getText().trim();
    }

    public String getDate() {
        return dateTextField.getText().trim();
    }

    public String getTime() {
        return timeTextField.getText().trim();
    }

    public String getVenue() {
        return venueTextField.getText().trim();
    }

    public String getAddress() {
        return addressTextField.getText().trim();
    }

    public String getDressCode() {
        return dressCodeTextField.getText().trim();
    }

    public void setBrideName(String text) {
        brideNameTextField.setText(text);
    }

    public void setGroomName(String text) {
        groomNameTextField.setText(text);
    }

    public void setDate(String text) {
        dateTextField.setText(text);
    }

    public void setTime(String text) {
        timeTextField.setText(text);
    }

    public void setVenue(String text) {
        venueTextField.setText(text);
    }

    public void setAddress(String text) {
        addressTextField.setText(text);
    }

    public void setDressCode(String text) {
        dressCodeTextField.setText(text);
    }

    public void clearFields() {
        brideNameTextField.setText("");
        groomNameTextField.setText("");
        dateTextField.setText("");
        timeTextField.setText("");
        venueTextField.setText("");
        addressTextField.setText("");
        dressCodeTextField.setText("");
    }
}
