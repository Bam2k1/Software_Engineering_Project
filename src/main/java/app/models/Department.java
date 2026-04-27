package app.models;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private String deptId;
    private String name;
    private String description;
    private User headOfDept;
    private List<User> members;
    private NoticeBoard noticeBoard;

    public Department(String deptId, String name, String description) {
        this.deptId = deptId;
        this.name = name;
        this.description = description;
        this.members = new ArrayList<>();
    }

    public void addNoticeBoard(NoticeBoard board) {
        this.noticeBoard = board;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public List<User> getMembers()     { return members; }
    public NoticeBoard getNoticeBoard(){ return noticeBoard; }

    public void assignHead(User user) {
        this.headOfDept = user;
    }

    public String getDeptId() { return deptId; }
    public String getName()   { return name; }
}
