package views.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GuestListTabPanel extends JPanel {
    private JTable guestTable;
    private DefaultTableModel guestTableModel;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public GuestListTabPanel() {
        setBackground(new Color(0, 206, 209));
        setLayout(new BorderLayout(10, 10));

        String[] columnNames = { "Name", "Mobile No", "With Family", "Guest ID" };
        guestTableModel = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 2:
                        return Boolean.class; // With Family
                    case 3:
                        return Integer.class; // Guest ID
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        guestTable = new JTable(guestTableModel);
        guestTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        guestTable.setFillsViewportHeight(true);
        guestTable.setRowHeight(25);
        guestTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        guestTable.setFont(new Font("SansSerif", Font.PLAIN, 13));

        // Hide Guest ID column
        guestTable.getColumnModel().getColumn(3).setMinWidth(0);
        guestTable.getColumnModel().getColumn(3).setMaxWidth(0);
        guestTable.getColumnModel().getColumn(3).setWidth(0);

        JScrollPane tableScrollPane = new JScrollPane(guestTable);
        add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        addButton = new JButton("Add Guest");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        buttonPanel.add(addButton);

        updateButton = new JButton("Update Guest");
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        buttonPanel.add(updateButton);

        deleteButton = new JButton("Delete Guest");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public DefaultTableModel getGuestTableModel() {
        return guestTableModel;
    }

    public JTable getGuestTable() {
        return guestTable;
    }

    public void setAddGuestListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setUpdateGuestListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void setDeleteGuestListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
}
