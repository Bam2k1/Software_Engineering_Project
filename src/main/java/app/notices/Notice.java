package app.notices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.enums.Priority;
import app.models.Attachment;
import app.models.Category;
import app.models.User;

public abstract class Notice {

    private String noticeId;
    private String title;
    private String content;
    private Date datePosted;
    private Date expiryDate;
    private Priority priority;
    private User author;
    private Category category;
    private List<Attachment> attachments;
    private boolean archived;

    public Notice(String noticeId, String title, String content, User author) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.datePosted = new Date();
        this.attachments = new ArrayList<>();
        this.priority = Priority.MEDIUM;
        this.archived = false;
    }

    public void publish() {
        System.out.println("Notice published: " + title);
    }

    public void archive() {
        this.archived = true;
    }

    public boolean isExpired() {
        if (expiryDate == null) return false;
        return new Date().after(expiryDate);
    }

    public void addAttachment(Attachment file) {
        attachments.add(file);
    }

    public abstract String getDetails();

    public void onPost() {
        // default: nothing extra happens. Subclasses override for type-specific behavior.
    }

    public String getNoticeId()        { return noticeId; }
    public String getTitle()           { return title; }
    public String getContent()         { return content; }
    public Date getDatePosted()        { return datePosted; }
    public User getAuthor()            { return author; }
    public Priority getPriority()      { return priority; }
    public boolean isArchived()        { return archived; }
    public void setPriority(Priority p){ this.priority = p; }
    public void setExpiryDate(Date d)  { this.expiryDate = d; }
    public void setCategory(Category c){ this.category = c; }
}
