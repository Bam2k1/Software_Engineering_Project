package app.interfaces;

import app.notices.Notice;

public interface Observer {
    void update(Notice notice);
}
