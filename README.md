# Notice Board System

![CI](https://github.com/Bam2k1/Software_Engineering_Project/actions/workflows/ci.yml/badge.svg)

A Java-based notice board system built for university departments. Departments can post notices to their boards, and subscribed users get notified in real time. Supports multiple notice types, user roles, and keyword search.

---

## What it does

- Users (students, faculty, admins) can subscribe to department notice boards
- When a notice is posted, all subscribers are notified automatically
- Three notice types: **Urgent**, **General**, and **Event**
- Admins can remove notices and users can unsubscribe at any time
- Keyword search across notice titles and content

---

## Design Patterns

**Observer Pattern**
`NoticeBoard` acts as the Subject and `User` acts as the Observer. When `addNotice()` is called, the board automatically calls `update()` on every subscribed user. This keeps the notification logic completely decoupled from the board itself — adding a new type of subscriber (email, SMS, etc.) would require zero changes to `NoticeBoard`.

**Factory Method Pattern**
`NoticeFactory` handles the creation of all notice types. Calling code never needs to know which concrete subclass it's getting — it just passes a type string and gets back a `Notice`. Adding a new notice type means creating one new subclass and one new case in the factory, nothing else changes.

---

## Project Structure

```
src/
├── main/java/app/
│   ├── Main.java               # Demo scenario
│   ├── enums/
│   │   ├── UserRole.java       # ADMIN, FACULTY, STUDENT
│   │   └── Priority.java       # HIGH, MEDIUM, LOW
│   ├── interfaces/
│   │   ├── Observer.java
│   │   └── Subject.java
│   ├── models/
│   │   ├── User.java           # Implements Observer
│   │   ├── NoticeBoard.java    # Implements Subject
│   │   ├── Department.java
│   │   ├── Category.java
│   │   └── Attachment.java
│   └── notices/
│       ├── Notice.java         # Abstract base
│       ├── GeneralNotice.java
│       ├── UrgentNotice.java
│       ├── EventNotice.java
│       └── NoticeFactory.java
└── test/java/app/
    ├── NoticeBoardTest.java
    ├── NoticeFactoryTest.java
    ├── NoticeTest.java
    ├── DepartmentTest.java
    └── UserTest.java
```

---

## Running the Demo

Make sure you have JDK 21 installed.

```bash
# Compile
javac -d out $(find src/main/java -name "*.java")

# Run
java -cp out app.Main
```

---

## Running Tests

Tests use JUnit 5 and are run through Maven.

```bash
mvn test
```

CI runs automatically on every push and pull request to `main` and `dev` via GitHub Actions. Test results are uploaded as artifacts on each run.

---

## Branch Strategy

- `main` — stable, demo-ready code
- `dev` — active development, merged into main when stable
