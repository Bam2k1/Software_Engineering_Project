# Final Report — Notice Board System

## Overview

We built a notice board system for university departments. The idea was simple: students miss important announcements when departments rely on email or physical boards, so we designed a system where users subscribe to the boards they care about and get notified the moment something is posted.

The project is written in Java and uses two design patterns (Observer and Factory Method) to keep the system flexible and easy to extend.

## What it does

- Students, faculty, and admins can each have an account
- Notice boards live inside departments
- Users subscribe to boards and get notified automatically when new notices appear
- Three notice types: Urgent, General, and Event — each formatted differently
- Admins can post and remove notices
- Keyword search across all notices on a board

## Design Decisions

**Observer pattern.** Our `NoticeBoard` class implements a `Subject` interface and our `User` class implements `Observer`. When `addNotice()` runs, the board calls `notifyObservers()` which loops through every subscriber and calls their `update()` method. The board doesn't know or care what its subscribers are — they just need to implement the interface. This means we could add an email service or SMS service later without changing any code in `NoticeBoard`.

**Factory Method pattern.** `Notice` is abstract with three concrete subclasses (`UrgentNotice`, `GeneralNotice`, `EventNotice`). A `NoticeFactory` class handles creation — calling code just asks for a notice by type string and gets back the right subclass. If we wanted a `MaintenanceNotice` next semester, we'd add one new class and one new line in the factory. Nothing else changes.

Both patterns serve the same goal: keeping different parts of the system loosely coupled so changes don't ripple through the codebase.

## Engineering Practices

- **Version control with GitHub.** We use a two-branch model — `main` for stable code and `dev` for active development.
- **Automated testing with JUnit 5.** Tests cover real behavior, not just getters. For example, we verify that subscribing twice doesn't double-notify and that unsubscribed users genuinely stop receiving updates.
- **Continuous integration with GitHub Actions.** Every push and pull request triggers a clean Ubuntu build and full test run. Test results are uploaded as artifacts so we can review them later.
- **Team roles.** Each member owned a clear part of the project — diagrams, implementation, testing, and presentation.

## Reflection

The biggest takeaway for me was how much the two design patterns changed the shape of the code. The first draft of the diagram had `User` and `NoticeBoard` directly tied together, and adding new notice types meant editing existing classes. Once we introduced the Observer interface and the factory, the dependencies inverted — calling code stopped depending on concrete classes and started depending on abstractions instead. The system became easier to extend without breaking what already worked.

If I were doing this again I'd write more tests earlier in the process. We added most of them near the end, which meant a few small refactors had to happen to make the code testable. Writing tests as we went would have caught those issues sooner.
