package app.models;

import java.util.ArrayList;
import java.util.List;
import app.enums.UserRole;
import app.interfaces.Observer;
import app.notices.Notice;

public class User implements Observer {

    private String userId;
    private String name;
    private String email;
    private UserRole role;
    private String password;
    private List<Notice> receivedNotices;

    public User(String userId, String name, String email, UserRole role, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.receivedNotices = new ArrayList<>();
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void logout() {
        System.out.println(name + " logged out.");
    }

    @Override
    public void update(Notice notice) {
        receivedNotices.add(notice);
        System.out.println("  [Notification -> " + name + "] " + notice.getDetails());
    }

    public List<Notice> viewNotices() {
        return receivedNotices;
    }

    public String getUserId() { return userId; }
    public String getName()   { return name; }
    public String getEmail()  { return email; }
    public UserRole getRole() { return role; }
}
