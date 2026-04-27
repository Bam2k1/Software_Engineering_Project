package app.notices;

import app.models.User;

public class GeneralNotice extends Notice {

    public GeneralNotice(String noticeId, String title, String content, User author) {
        super(noticeId, title, content, author);
    }

    @Override
    public String getDetails() {
        return "[General] " + getTitle() + ": " + getContent();
    }
}
