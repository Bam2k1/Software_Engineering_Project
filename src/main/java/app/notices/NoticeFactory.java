package app.notices;

import app.models.User;

public class NoticeFactory {

    public Notice createNotice(String type, String noticeId, String title, String content, User author) {
        switch (type.toUpperCase()) {
            case "URGENT":  return new UrgentNotice(noticeId, title, content, author);
            case "GENERAL": return new GeneralNotice(noticeId, title, content, author);
            case "EVENT":   return new EventNotice(noticeId, title, content, author);
            default: throw new IllegalArgumentException("Unknown notice type: " + type);
        }
    }
}
