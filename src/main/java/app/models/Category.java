package app.models;

import java.util.ArrayList;
import java.util.List;
import app.notices.Notice;

public class Category {

    private String categoryId;
    private String name;
    private String description;
    private List<Notice> notices;

    public Category(String categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.notices = new ArrayList<>();
    }

    public void addNotice(Notice notice) {
        notices.add(notice);
    }

    public List<Notice> getNotices()    { return notices; }
    public String getCategoryId()       { return categoryId; }
    public String getName()             { return name; }

    public void updateCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void deleteCategory() {
        notices.clear();
    }
}
