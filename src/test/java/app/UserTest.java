package app;

import app.enums.UserRole;
import app.models.User;
import app.notices.GeneralNotice;
import app.notices.Notice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("U1", "Alice", "alice@test.edu", UserRole.STUDENT, "secret");
    }

    @Test
    void loginSucceedsWithCorrectCredentials() {
        assertTrue(user.login("alice@test.edu", "secret"));
    }

    @Test
    void loginFailsWithWrongPassword() {
        assertFalse(user.login("alice@test.edu", "wrong"));
    }

    @Test
    void loginFailsWithWrongEmail() {
        assertFalse(user.login("other@test.edu", "secret"));
    }

    @Test
    void viewNoticesIsEmptyInitially() {
        assertTrue(user.viewNotices().isEmpty());
    }

    @Test
    void updateAddsNoticeToReceivedList() {
        Notice n = new GeneralNotice("N1", "Test", "Content",
                new User("U2", "Admin", "admin@test.edu", UserRole.ADMIN, "pass"));
        user.update(n);
        assertEquals(1, user.viewNotices().size());
        assertEquals("N1", user.viewNotices().get(0).getNoticeId());
    }
}
