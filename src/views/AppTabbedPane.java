package views;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import views.tabs.*;

public class AppTabbedPane extends JTabbedPane {

    private HomeTabPanel homeTab;
    private InviteTabPanel inviteTab;
    private GuestListTabPanel guestListTab;
    private SentHistoryTabPanel sentHistoryTab;
    private ReceivedInvitesTabPanel receivedInvitesTab;
    private ProfileTabPanel profileTab;
    private AboutUsTabPanel aboutUsTab;

    public AppTabbedPane() {
        homeTab = new HomeTabPanel();
        addTab("Home", homeTab);

        guestListTab = new GuestListTabPanel();
        addTab("Guest List", guestListTab);

        inviteTab = new InviteTabPanel();
        addTab("Create/Edit Invite", inviteTab);

        sentHistoryTab = new SentHistoryTabPanel();
        addTab("Sent History", sentHistoryTab);

        receivedInvitesTab = new ReceivedInvitesTabPanel();
        addTab("Received Invites", receivedInvitesTab);

        profileTab = new ProfileTabPanel();
        addTab("Profile", profileTab);

        aboutUsTab = new AboutUsTabPanel();
        addTab("About Us", aboutUsTab);
    }

    public HomeTabPanel getHomeTab() {
        return homeTab;
    }

    public InviteTabPanel getInviteTab() {
        return inviteTab;
    }

    public GuestListTabPanel getGuestListTab() {
        return guestListTab;
    }

    public SentHistoryTabPanel getSentHistoryTab() {
        return sentHistoryTab;
    }

    public ReceivedInvitesTabPanel getReceivedInvitesTab() {
        return receivedInvitesTab;
    }

    public ProfileTabPanel getProfileTab() {
        return profileTab;
    }

    public void setTabChangeListener(ChangeListener listener) {
        this.addChangeListener(listener);
    }
}
