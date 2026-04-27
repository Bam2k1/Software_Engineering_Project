package app.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import app.interfaces.Observer;
import app.interfaces.Subject;
import app.notices.Notice;

public class NoticeBoard implements Subject {

    private String boardId;
    private String title;
    private List<Notice> notices;
    private List<Observer> observers;

    public NoticeBoard(String boardId, String title) {
        this.boardId = boardId;
        this.title = title;
        this.notices = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addNotice(Notice notice) {
        notices.add(notice);
        notifyObservers(notice);
    }

    public void removeNotice(String noticeId) {
        notices.removeIf(n -> n.getNoticeId().equals(noticeId));
    }

    public List<Notice> getActiveNotices() {
        return notices.stream()
                .filter(n -> !n.isExpired() && !n.isArchived())
                .collect(Collectors.toList());
    }

    public List<Notice> searchNotices(String keyword) {
        String lower = keyword.toLowerCase();
        return notices.stream()
                .filter(n -> n.getTitle().toLowerCase().contains(lower)
                          || n.getContent().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    @Override
    public void subscribe(Observer obs) {
        if (!observers.contains(obs)) observers.add(obs);
    }

    @Override
    public void unsubscribe(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers(Notice notice) {
        for (Observer obs : observers) obs.update(notice);
    }

    public String getBoardId()      { return boardId; }
    public String getTitle()        { return title; }
    public List<Notice> getNotices(){ return notices; }
}
