package controllers;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Guest;
import models.GuestDAO;
import models.HistoryDAO;
import models.Invitation;
import models.InvitationDAO;
import models.ReceivedInvitation;
import models.SentInvitation;
import models.User;
import models.UserDAO;
import views.GuestDialog;
import views.MainFrame;
import views.tabs.GuestListTabPanel;
import views.tabs.HomeTabPanel;
import views.tabs.InviteTabPanel;
import views.tabs.ProfileTabPanel;
import views.tabs.ReceivedInvitesTabPanel;
import views.tabs.SentHistoryTabPanel;

public class AppController {
    private MainFrame mainFrame;
    private User currentUser;

    private UserDAO userDAO;
    private GuestDAO guestDAO;
    private InvitationDAO invitationDAO;
    private HistoryDAO historyDAO;

    public AppController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.userDAO = new UserDAO();
        this.guestDAO = new GuestDAO();
        this.invitationDAO = new InvitationDAO();
        this.historyDAO = new HistoryDAO();

        initListeners();
    }

    private void initListeners() {
        // Home Tab
        HomeTabPanel homeTab = mainFrame.getAppTabbedPane().getHomeTab();
        homeTab.setLogoutListener(e -> handleLogout());
        homeTab.setSendInviteListener(e -> sendInvitations());

        // Profile Tab
        ProfileTabPanel profileTab = mainFrame.getAppTabbedPane().getProfileTab();
        profileTab.setSaveProfileListener(e -> handleUpdateProfile());
        profileTab.setChangePasswordListener(e -> handleChangePassword());

        // Guest List Tab
        GuestListTabPanel guestTab = mainFrame.getAppTabbedPane().getGuestListTab();
        guestTab.setAddGuestListener(e -> handleAddGuest());
        guestTab.setUpdateGuestListener(e -> handleUpdateGuest());
        guestTab.setDeleteGuestListener(e -> handleDeleteGuest());

        // Invite Tab
        InviteTabPanel inviteTab = mainFrame.getAppTabbedPane().getInviteTab();
        inviteTab.setSaveListener(e -> handleSaveInviteDetails());

        // Tab changes
        mainFrame.getAppTabbedPane().setTabChangeListener(e -> refreshActiveTab());
    }

    public void initializeForUser(User user) {
        this.currentUser = user;
        try {
            User fullUser = userDAO.getUserByMobile(user.getMobile());
            if (fullUser != null) {
                this.currentUser = fullUser;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        mainFrame.getAppTabbedPane().setSelectedIndex(0); // Switch to Home tab
        loadProfileData();
        loadInvitationDetails();
        loadGuestList();
        loadSentHistory();
        loadReceivedInvites();
        updateHomePreview();
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.currentUser = null;
            mainFrame.getAppTabbedPane().getInviteTab().clearFields();
            mainFrame.getAppTabbedPane().getProfileTab().setSecurityAnswerInput("");
            mainFrame.getAppTabbedPane().getGuestListTab().getGuestTableModel().setRowCount(0);
            mainFrame.getAppTabbedPane().getSentHistoryTab().clearHistory();
            mainFrame.getAppTabbedPane().getReceivedInvitesTab().clearInvites();
            mainFrame.getAppTabbedPane().getHomeTab().updateTextArea("");

            mainFrame.showView("Login");
        }
    }

    private void refreshActiveTab() {
        if (currentUser == null)
            return;
        int activeIndex = mainFrame.getAppTabbedPane().getSelectedIndex();
        switch (activeIndex) {
            case 0:
                updateHomePreview();
                break; // Home
            case 1:
                loadGuestList();
                break; // Guest List
            case 2:
                loadInvitationDetails();
                break; // Invite details
            case 3:
                loadSentHistory();
                break; // Sent History
            case 4:
                loadReceivedInvites();
                break; // Received
            case 5:
                loadProfileData();
                break; // Profile
        }
    }

    private void loadProfileData() {
        if (currentUser == null)
            return;
        ProfileTabPanel profileTab = mainFrame.getAppTabbedPane().getProfileTab();
        profileTab.setNameInput(currentUser.getName());
        profileTab.setMobileInput(currentUser.getMobile());
        profileTab.setAgeInput(currentUser.getAge());
        profileTab.setGenderInput(currentUser.getGender());
        profileTab.setSecurityQuestionInput(currentUser.getSecurityQuestion());
        profileTab.setSecurityAnswerInput(currentUser.getSecurityAnswer());
    }

    private void handleUpdateProfile() {
        ProfileTabPanel profileTab = mainFrame.getAppTabbedPane().getProfileTab();
        currentUser.setName(profileTab.getNameInput());
        currentUser.setAge(profileTab.getAgeInput());
        currentUser.setGender(profileTab.getGenderInput());
        currentUser.setSecurityQuestion(profileTab.getSecurityQuestionInput());
        currentUser.setSecurityAnswer(profileTab.getSecurityAnswerInput());

        try {
            if (userDAO.updateUserProfile(currentUser)) {
                JOptionPane.showMessageDialog(mainFrame, "Profile updated successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                updateHomePreview();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Failed to update profile.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleChangePassword() {
        JOptionPane.showMessageDialog(mainFrame, "Change password functionality is not yet fully implemented.",
                "Feature Coming Soon", JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadInvitationDetails() {
        if (currentUser == null)
            return;
        try {
            Invitation inv = invitationDAO.getInvitation(currentUser.getMobile());
            InviteTabPanel inviteTab = mainFrame.getAppTabbedPane().getInviteTab();
            if (inv != null) {
                inviteTab.setBrideName(inv.getBrideName());
                inviteTab.setGroomName(inv.getGroomName());
                inviteTab.setDate(inv.getEventDate());
                inviteTab.setTime(inv.getEventTime());
                inviteTab.setVenue(inv.getVenue());
                inviteTab.setAddress(inv.getAddress());
                inviteTab.setDressCode(inv.getDressCode());
            } else {
                inviteTab.clearFields();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void handleSaveInviteDetails() {
        InviteTabPanel inviteTab = mainFrame.getAppTabbedPane().getInviteTab();
        String brideName = inviteTab.getBrideName();
        String groomName = inviteTab.getGroomName();
        String eventDate = inviteTab.getDate();

        if (brideName.isEmpty() || groomName.isEmpty() || eventDate.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Bride's Name, Groom's Name, and Date are required.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Invitation existingInv = invitationDAO.getInvitation(currentUser.getMobile());
            Invitation inv = new Invitation();
            inv.setUserMobile(currentUser.getMobile());
            inv.setBrideName(brideName);
            inv.setGroomName(groomName);
            inv.setEventDate(eventDate);
            inv.setEventTime(inviteTab.getTime());
            inv.setVenue(inviteTab.getVenue());
            inv.setAddress(inviteTab.getAddress());
            inv.setDressCode(inviteTab.getDressCode());

            boolean success;
            if (existingInv != null) {
                inv.setId(existingInv.getId());
                success = invitationDAO.updateInvitation(inv);
            } else {
                success = invitationDAO.insertInvitation(inv);
            }

            if (success) {
                JOptionPane.showMessageDialog(mainFrame, "Invitation details saved correctly!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                updateHomePreview();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Failed to save details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateHomePreview() {
        if (currentUser == null)
            return;
        try {
            Invitation inv = invitationDAO.getInvitation(currentUser.getMobile());
            StringBuilder builder = new StringBuilder();
            builder.append("=========================================\n");
            builder.append("           WEDDING INVITATION            \n");
            builder.append("=========================================\n\n");
            builder.append("Dear [Guest Name],\n\n");
            builder.append("We joyfully invite you to share in our happiness as\n\n");

            if (inv != null) {
                builder.append("        ")
                        .append(inv.getBrideName() != null && !inv.getBrideName().isEmpty() ? inv.getBrideName()
                                : "[Bride's Name]")
                        .append("\n");
                builder.append("                 and\n");
                builder.append("        ")
                        .append(inv.getGroomName() != null && !inv.getGroomName().isEmpty() ? inv.getGroomName()
                                : "[Groom's Name]")
                        .append("\n\n");
                builder.append("unite in marriage.\n\n");
                builder.append("Date: ")
                        .append(inv.getEventDate() != null && !inv.getEventDate().isEmpty() ? inv.getEventDate()
                                : "[Event Date]")
                        .append("\n");
                builder.append("Time: ").append(
                        inv.getEventTime() != null && !inv.getEventTime().isEmpty() ? inv.getEventTime() : "[Time]")
                        .append("\n");
                builder.append("Venue: ")
                        .append(inv.getVenue() != null && !inv.getVenue().isEmpty() ? inv.getVenue() : "[Venue Name]")
                        .append("\n");

                if (inv.getAddress() != null && !inv.getAddress().isEmpty()) {
                    builder.append("Address: ").append(inv.getAddress()).append("\n");
                }
                if (inv.getDressCode() != null && !inv.getDressCode().isEmpty()) {
                    builder.append("\nDress Code: ").append(inv.getDressCode()).append("\n");
                }
            } else {
                builder.append("        [Bride's Name]\n");
                builder.append("                 and\n");
                builder.append("        [Groom's Name]\n\n");
                builder.append("unite in marriage.\n\n");
                builder.append("Date: [Event Date]\n");
                builder.append("Time: [Time]\n");
                builder.append("Venue: [Venue Name]\n");
            }

            builder.append("\nPlease join us!\n\n");
            builder.append("With love,\n");
            builder.append(currentUser.getName() != null ? currentUser.getName() : "[Your Name]").append("\n");
            builder.append("=========================================\n");

            mainFrame.getAppTabbedPane().getHomeTab().updateTextArea(builder.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadGuestList() {
        if (currentUser == null)
            return;
        try {
            List<Guest> guests = guestDAO.getGuestsByUser(currentUser.getMobile());
            DefaultTableModel model = mainFrame.getAppTabbedPane().getGuestListTab().getGuestTableModel();
            model.setRowCount(0);
            for (Guest g : guests) {
                model.addRow(new Object[] { g.getName(), g.getMobile(), g.isWithFamily(), g.getId() });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void handleAddGuest() {
        GuestDialog dialog = new GuestDialog(mainFrame, "Add New Guest", "Save Guest");
        dialog.setSaveListener(e -> {
            String name = dialog.getGuestName();
            String mobile = dialog.getGuestMobile();
            if (name.isEmpty() || mobile.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Name and Mobile cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Guest guest = new Guest(0, currentUser.getMobile(), name, mobile, dialog.isWithFamily());
            try {
                if (guestDAO.addGuest(guest) != -1) {
                    JOptionPane.showMessageDialog(dialog, "Guest Added!");
                    dialog.dispose();
                    loadGuestList();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to add guest.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Database error: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.setVisible(true);
    }

    private void handleUpdateGuest() {
        GuestListTabPanel guestTab = mainFrame.getAppTabbedPane().getGuestListTab();
        int selectedRow = guestTab.getGuestTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(mainFrame, "Please select ONE guest to update.", "Selection Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = guestTab.getGuestTableModel();
        String currentName = (String) model.getValueAt(selectedRow, 0);
        String currentMobile = (String) model.getValueAt(selectedRow, 1);
        boolean currentWithFamily = (Boolean) model.getValueAt(selectedRow, 2);
        int guestId = (Integer) model.getValueAt(selectedRow, 3);

        GuestDialog dialog = new GuestDialog(mainFrame, "Update Guest", "Update Guest");
        dialog.setGuestName(currentName);
        dialog.setGuestMobile(currentMobile);
        dialog.setWithFamily(currentWithFamily);

        dialog.setSaveListener(e -> {
            String name = dialog.getGuestName();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Guest guest = new Guest(guestId, currentUser.getMobile(), name, currentMobile, dialog.isWithFamily());
            try {
                if (guestDAO.updateGuest(guest)) {
                    JOptionPane.showMessageDialog(dialog, "Guest updated successfully.");
                    dialog.dispose();
                    loadGuestList();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Database error: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.setVisible(true);
    }

    private void handleDeleteGuest() {
        GuestListTabPanel guestTab = mainFrame.getAppTabbedPane().getGuestListTab();
        int[] selectedRows = guestTab.getGuestTable().getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(mainFrame, "Please select guest(s) to delete.", "Selection Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(mainFrame,
                "Are you sure you want to delete " + selectedRows.length + " guest(s)?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int successCount = 0;
            try {
                for (int row : selectedRows) {
                    int guestId = (Integer) guestTab.getGuestTableModel().getValueAt(row, 3);
                    if (guestDAO.deleteGuest(guestId, currentUser.getMobile())) {
                        successCount++;
                    }
                }
                JOptionPane.showMessageDialog(mainFrame, "Successfully deleted " + successCount + " guest(s).",
                        "Deletion Complete", JOptionPane.INFORMATION_MESSAGE);
                loadGuestList();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadSentHistory() {
        if (currentUser == null)
            return;
        SentHistoryTabPanel sentTab = mainFrame.getAppTabbedPane().getSentHistoryTab();
        sentTab.clearHistory();
        try {
            List<SentInvitation> history = historyDAO.getSentHistory(currentUser.getMobile());
            if (history.isEmpty()) {
                sentTab.showNoHistoryMessage();
            } else {
                for (SentInvitation si : history) {
                    sentTab.addHistoryEntry(si.getSentTime(), si.getGuestName(), si.getGuestMobile(),
                            si.getBrideName(), si.getGroomName(), si.getEventDate());
                }
                sentTab.refreshPanel();
            }
        } catch (SQLException ex) {
            sentTab.showErrorMessage(ex.getMessage());
        }
    }

    private void loadReceivedInvites() {
        if (currentUser == null)
            return;
        ReceivedInvitesTabPanel receivedTab = mainFrame.getAppTabbedPane().getReceivedInvitesTab();
        receivedTab.clearInvites();
        try {
            List<ReceivedInvitation> invites = historyDAO.getReceivedInvitations(currentUser.getMobile());
            if (invites.isEmpty()) {
                receivedTab.showNoInvitesMessage();
            } else {
                for (ReceivedInvitation ri : invites) {
                    receivedTab.addReceivedInvite(ri.getReceivedTime(), ri.getStatus(), ri.getSenderName(),
                            ri.getBrideName(), ri.getGroomName(), ri.getEventDate(),
                            ri.getEventTime(), ri.getVenue(), ri.getAddress(),
                            ri.getDressCode(), ri.getInvitationId());
                }
                receivedTab.refreshPanel();
            }
        } catch (SQLException ex) {
            receivedTab.showErrorMessage(ex.getMessage());
        }
    }

    private void sendInvitations() {
        GuestListTabPanel guestTab = mainFrame.getAppTabbedPane().getGuestListTab();
        int[] selectedRows = guestTab.getGuestTable().getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Current implementation logic is simple. " +
                            "You must go to the 'Guest List' tab, select guests, and then return here to send.",
                    "No Guests Selected", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.getAppTabbedPane().setSelectedIndex(1); // Switch to Guest List tab
            return;
        }

        try {
            int currentInvitationId = invitationDAO.getCurrentInvitationId(currentUser.getMobile());
            if (currentInvitationId == -1) {
                JOptionPane.showMessageDialog(mainFrame,
                        "You have not created an invitation yet. Please fill out details in 'Create/Edit Invite' tab first.",
                        "Invitation Details Missing", JOptionPane.WARNING_MESSAGE);
                mainFrame.getAppTabbedPane().setSelectedIndex(2); // Switch to Create Invite tab
                return;
            }

            int successCount = 0;
            DefaultTableModel model = guestTab.getGuestTableModel();

            for (int row : selectedRows) {
                int guestId = (Integer) model.getValueAt(row, 3);
                String guestMobile = (String) model.getValueAt(row, 1);

                // Record sent
                if (historyDAO.recordSentInvitation(currentInvitationId, guestId, currentUser.getMobile())) {
                    successCount++;
                }

                // Record received if they are a registered user
                if (userDAO.checkUserExists(guestMobile)) {
                    historyDAO.recordReceivedInvitation(guestMobile, currentInvitationId, currentUser.getMobile());
                }
            }

            JOptionPane.showMessageDialog(mainFrame, "Successfully sent invitation to " + successCount + " guest(s).",
                    "Send Complete", JOptionPane.INFORMATION_MESSAGE);
            guestTab.getGuestTable().clearSelection();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Database error while sending: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
