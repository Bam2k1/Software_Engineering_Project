package app.interfaces;

import app.notices.Notice;

public interface Subject {
    void subscribe(Observer obs);
    void unsubscribe(Observer obs);
    void notifyObservers(Notice notice);
}
