package app.notices;

import app.enums.Priority;
import app.models.User;

public class UrgentNotice extends Notice {

    public UrgentNotice(String noticeId, String title, String content, User author) {
        super(noticeId, title, content, author);
        setPriority(Priority.HIGH);
    }

    @Override
    public String getDetails() {
        return "[URGENT] " + getTitle() + ": " + getContent();
    }

    public void escalate() {
        System.out.println("ESCALATING: " + getTitle());
    }
}
