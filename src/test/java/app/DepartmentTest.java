package app;

import app.enums.UserRole;
import app.models.Department;
import app.models.NoticeBoard;
import app.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {

    private Department dept;
    private User alice;
    private User bob;

    @BeforeEach
    void setUp() {
        dept  = new Department("D1", "CS", "Computer Science Department");
        alice = new User("U1", "Alice", "alice@test.edu", UserRole.STUDENT, "pass");
        bob   = new User("U2", "Bob",   "bob@test.edu",   UserRole.STUDENT, "pass");
    }

    @Test
    void newDepartmentHasNoMembers() {
        assertTrue(dept.getMembers().isEmpty());
    }

    @Test
    void addMemberAddsToMembersList() {
        dept.addMember(alice);
        assertEquals(1, dept.getMembers().size());
        assertEquals(alice, dept.getMembers().get(0));
    }

    @Test
    void addMultipleMembersWorks() {
        dept.addMember(alice);
        dept.addMember(bob);
        assertEquals(2, dept.getMembers().size());
    }

    @Test
    void noticeBoardStartsNull() {
        assertNull(dept.getNoticeBoard());
    }

    @Test
    void addNoticeBoardSetsBoard() {
        NoticeBoard board = new NoticeBoard("B1", "CS Board");
        dept.addNoticeBoard(board);
        assertEquals(board, dept.getNoticeBoard());
    }

    @Test
    void deptIdAndNameArePreserved() {
        assertEquals("D1", dept.getDeptId());
        assertEquals("CS", dept.getName());
    }
}
