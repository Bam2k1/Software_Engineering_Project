package app.notices;

import java.util.Date;
import app.models.User;

public class EventNotice extends Notice {

    private Date eventDate;
    private String venue;

    public EventNotice(String noticeId, String title, String content, User author) {
        super(noticeId, title, content, author);
        this.venue = "TBD";
    }

    public EventNotice(String noticeId, String title, String content, User author, Date eventDate, String venue) {
        super(noticeId, title, content, author);
        this.eventDate = eventDate;
        this.venue = venue;
    }

    @Override
    public String getDetails() {
        return "[Event] " + getTitle() + " @ " + venue + ": " + getContent();
    }

    @Override
    public void onPost() {
        if (eventDate != null) {
            System.out.println("  [Event scheduled] " + getTitle() + " at " + venue + " on " + eventDate);
        }
    }

    public Date getEventDate() { return eventDate; }
    public String getVenue()   { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public void setEventDate(Date d)   { this.eventDate = d; }
}
