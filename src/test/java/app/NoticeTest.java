package app;

import app.enums.Priority;
import app.enums.UserRole;
import app.models.User;
import app.notices.EventNotice;
import app.notices.GeneralNotice;
import app.notices.Notice;
import app.notices.UrgentNotice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class NoticeTest {

    private User author;

    @BeforeEach
    void setUp() {
        author = new User("U1", "Admin", "admin@test.edu", UserRole.ADMIN, "pass");
    }

    @Test
    void newNoticeIsNotExpiredByDefault() {
        Notice n = new GeneralNotice("N1", "Title", "Content", author);
        assertFalse(n.isExpired());
    }

    @Test
    void noticeWithPastExpiryIsExpired() {
        Notice n = new GeneralNotice("N1", "Title", "Content", author);
        n.setExpiryDate(new Date(System.currentTimeMillis() - 100000));
        assertTrue(n.isExpired());
    }

    @Test
    void noticeWithFutureExpiryIsNotExpired() {
        Notice n = new GeneralNotice("N1", "Title", "Content", author);
        n.setExpiryDate(new Date(System.currentTimeMillis() + 100000));
        assertFalse(n.isExpired());
    }

    @Test
    void archiveSetsArchivedFlag() {
        Notice n = new GeneralNotice("N1", "Title", "Content", author);
        assertFalse(n.isArchived());
        n.archive();
        assertTrue(n.isArchived());
    }

    @Test
    void defaultPriorityIsMedium() {
        Notice n = new GeneralNotice("N1", "Title", "Content", author);
        assertEquals(Priority.MEDIUM, n.getPriority());
    }

    @Test
    void setPriorityChangesPriority() {
        Notice n = new GeneralNotice("N1", "Title", "Content", author);
        n.setPriority(Priority.LOW);
        assertEquals(Priority.LOW, n.getPriority());
    }

    @Test
    void urgentNoticeHasHighPriorityByDefault() {
        Notice n = new UrgentNotice("N1", "Server Down", "Down now", author);
        assertEquals(Priority.HIGH, n.getPriority());
    }

    @Test
    void urgentNoticeOnPostRunsWithoutError() {
        Notice n = new UrgentNotice("N1", "Server Down", "Down now", author);
        assertDoesNotThrow(n::onPost);
    }

    @Test
    void eventNoticeOnPostHandlesNullEventDate() {
        Notice n = new EventNotice("N1", "Talk", "Tech talk", author);
        assertDoesNotThrow(n::onPost);
    }

    @Test
    void generalNoticeOnPostUsesDefaultNoOp() {
        Notice n = new GeneralNotice("N1", "Hello", "World", author);
        assertDoesNotThrow(n::onPost);
    }
}
