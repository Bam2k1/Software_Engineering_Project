package app;

import app.enums.Priority;
import app.enums.UserRole;
import app.models.User;
import app.notices.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoticeFactoryTest {

    private NoticeFactory factory;
    private User author;

    @BeforeEach
    void setUp() {
        factory = new NoticeFactory();
        author  = new User("U1", "Admin", "admin@test.edu", UserRole.ADMIN, "pass");
    }

    @Test
    void createUrgentNoticeReturnsCorrectType() {
        Notice n = factory.createNotice("URGENT", "N1", "Title", "Content", author);
        assertInstanceOf(UrgentNotice.class, n);
    }

    @Test
    void createGeneralNoticeReturnsCorrectType() {
        Notice n = factory.createNotice("GENERAL", "N1", "Title", "Content", author);
        assertInstanceOf(GeneralNotice.class, n);
    }

    @Test
    void createEventNoticeReturnsCorrectType() {
        Notice n = factory.createNotice("EVENT", "N1", "Title", "Content", author);
        assertInstanceOf(EventNotice.class, n);
    }

    @Test
    void urgentNoticeHasHighPriority() {
        Notice n = factory.createNotice("URGENT", "N1", "Title", "Content", author);
        assertEquals(Priority.HIGH, n.getPriority());
    }

    @Test
    void unknownTypeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.createNotice("MAINTENANCE", "N1", "Title", "Content", author));
    }

    @Test
    void noticeIdAndTitleArePreserved() {
        Notice n = factory.createNotice("GENERAL", "N42", "My Title", "My Content", author);
        assertEquals("N42", n.getNoticeId());
        assertEquals("My Title", n.getTitle());
    }
}
