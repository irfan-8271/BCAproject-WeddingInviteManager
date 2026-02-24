package models;

import java.sql.Timestamp;

public class SentInvitation {
    private Timestamp sentTime;
    private String guestName;
    private String guestMobile;
    private String brideName;
    private String groomName;
    private String eventDate;

    public SentInvitation(Timestamp sentTime, String guestName, String guestMobile, String brideName, String groomName,
            String eventDate) {
        this.sentTime = sentTime;
        this.guestName = guestName;
        this.guestMobile = guestMobile;
        this.brideName = brideName;
        this.groomName = groomName;
        this.eventDate = eventDate;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestMobile() {
        return guestMobile;
    }

    public String getBrideName() {
        return brideName;
    }

    public String getGroomName() {
        return groomName;
    }

    public String getEventDate() {
        return eventDate;
    }
}
