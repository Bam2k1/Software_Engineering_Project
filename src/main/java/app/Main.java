package app;

import java.util.Date;
import java.util.List;
import app.enums.UserRole;
import app.models.Department;
import app.models.NoticeBoard;
import app.models.User;
import app.notices.EventNotice;
import app.notices.Notice;
import app.notices.NoticeFactory;

public class Main {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("      Notice Board System - Demo        ");
        System.out.println("========================================\n");

        // --- Setup Department & Board ---
        Department csDept = new Department("D1", "Computer Science", "CS Department");
        NoticeBoard board = new NoticeBoard("B1", "CS Department Board");
        csDept.addNoticeBoard(board);

        // --- Create Users ---
        User admin = new User("U1", "Prof. Smith", "smith@school.edu", UserRole.ADMIN, "admin123");
        User alice = new User("U2", "Alice",       "alice@school.edu", UserRole.STUDENT, "pass1");
        User bob   = new User("U3", "Bob",         "bob@school.edu",   UserRole.STUDENT, "pass2");

        csDept.addMember(alice);
        csDept.addMember(bob);

        // --- Login Demo ---
        System.out.println(">> Login test (correct credentials): "
                + (admin.login("smith@school.edu", "admin123") ? "SUCCESS" : "FAILED"));
        System.out.println(">> Login test (wrong password):      "
                + (admin.login("smith@school.edu", "wrong") ? "SUCCESS" : "FAILED"));

        // --- Subscribe (Observer pattern) ---
        board.subscribe(alice);
        board.subscribe(bob);
        System.out.println("\n>> Alice and Bob subscribed to \"" + board.getTitle() + "\"\n");

        // --- Post Notices via Factory Method pattern ---
        NoticeFactory factory = new NoticeFactory();
        System.out.println(">> Admin posts notices — Observer pattern fires automatically:");

        Notice urgent = factory.createNotice("URGENT", "N1", "Server Downtime",
                "Servers down Saturday 2-4am for maintenance.", admin);
        board.addNotice(urgent);

        Notice general = factory.createNotice("GENERAL", "N2", "Library Hours",
                "Library closes at 9pm this week.", admin);
        board.addNotice(general);

        EventNotice event = new EventNotice("N3", "Hackathon",
                "Annual CS Hackathon — prizes for top 3 teams!", admin, new Date(), "Main Hall");
        board.addNotice(event);

        // --- Search ---
        System.out.println("\n>> Search results for \"server\":");
        List<Notice> results = board.searchNotices("server");
        results.forEach(n -> System.out.println("   " + n.getDetails()));

        // --- Active notice count ---
        System.out.println("\n>> Active notices on board: " + board.getActiveNotices().size());

        // --- Remove a notice ---
        board.removeNotice("N2");
        System.out.println(">> After removing 'Library Hours': " + board.getActiveNotices().size() + " active notices");

        // --- Observer received count ---
        System.out.println("\n>> Alice's total notifications received: " + alice.viewNotices().size());
        System.out.println(">> Bob's total notifications received:   " + bob.viewNotices().size());

        // --- Unsubscribe demo ---
        System.out.println("\n>> After Bob unsubscribes:");
        board.unsubscribe(bob);
        board.addNotice(factory.createNotice("GENERAL", "N4", "Exam Schedule",
                "Final exams start May 10.", admin));
        System.out.println("   Alice's notifications: " + alice.viewNotices().size());
        System.out.println("   Bob's notifications:   " + bob.viewNotices().size() + " (no new ones)");

        System.out.println("\n========================================");
        System.out.println("              Demo Complete             ");
        System.out.println("========================================");
    }
}
