package app;

import app.enums.UserRole;
import app.models.NoticeBoard;
import app.models.User;
import app.notices.GeneralNotice;
import app.notices.Notice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NoticeBoardTest {

    private NoticeBoard board;
    private User admin;
    private User student;

    @BeforeEach
    void setUp() {
        board   = new NoticeBoard("B1", "Test Board");
        admin   = new User("U1", "Admin", "admin@test.edu", UserRole.ADMIN, "pass");
        student = new User("U2", "Student", "student@test.edu", UserRole.STUDENT, "pass");
    }

    @Test
    void addNoticeNotifiesSubscribers() {
        board.subscribe(student);
        board.addNotice(new GeneralNotice("N1", "Hello", "World", admin));
        assertEquals(1, student.viewNotices().size());
    }

    @Test
    void removeNoticeDeletesFromBoard() {
        board.addNotice(new GeneralNotice("N1", "To Remove", "Content", admin));
        board.removeNotice("N1");
        assertTrue(board.getActiveNotices().isEmpty());
    }

    @Test
    void searchNoticesMatchesTitleAndContent() {
        board.addNotice(new GeneralNotice("N1", "Server Downtime", "Servers offline", admin));
        board.addNotice(new GeneralNotice("N2", "Library Hours", "Closes at 9pm", admin));

        List<Notice> results = board.searchNotices("server");
        assertEquals(1, results.size());
        assertEquals("N1", results.get(0).getNoticeId());
    }

    @Test
    void unsubscribedUserDoesNotReceiveNotifications() {
        board.subscribe(student);
        board.unsubscribe(student);
        board.addNotice(new GeneralNotice("N1", "Test", "Content", admin));
        assertTrue(student.viewNotices().isEmpty());
    }

    @Test
    void duplicateSubscribeOnlyAddsOnce() {
        board.subscribe(student);
        board.subscribe(student);
        board.addNotice(new GeneralNotice("N1", "Test", "Content", admin));
        assertEquals(1, student.viewNotices().size());
    }
}
