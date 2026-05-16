# Final Report - Notice Board System

## Overview

We built a notice board system for university departments. Students often miss important announcements when departments rely on email or physical boards, so we designed a system where users can subscribe to the boards they care about and get notified when something is posted.

The project is written in Java and uses two design patterns, Observer and Factory Method, to keep the system flexible.

## What it does

- Students, faculty, and admins can each have an account
- Notice boards belong to departments
- Users subscribe to boards and get notified automatically when new notices appear
- Three notice types: Urgent, General, and Event, each formatted differently
- Admins can post and remove notices
- Keyword search across all notices on a board

## Design Decisions

Observer pattern. Our `NoticeBoard` class implements a `Subject` interface and our `User` class implements `Observer`. When `addNotice()` runs, the board calls `notifyObservers()`, which loops through every subscriber and calls their `update()` method. The board does not need to know what its subscribers are. They just need to implement the interface. This means we could add an email service or SMS service later without changing any code in `NoticeBoard`.

Factory Method pattern. `Notice` is abstract with three concrete subclasses (`UrgentNotice`, `GeneralNotice`, `EventNotice`). A `NoticeFactory` class handles creation. Calling code just asks for a notice by type string and gets back the right subclass. If we wanted a `MaintenanceNotice` later, we would add one new class and one new line in the factory.

Both patterns serve the same goal: keeping the system flexible so changes in one part don't force changes everywhere else.

## Engineering Practices

- Version control with GitHub. We use a two-branch model with `main` for stable code and `dev` for active development.
- Automated testing with JUnit 5. Tests cover real behavior, not just getters. For example, we verify that subscribing twice doesn't double-notify and that unsubscribed users stop receiving updates.
- Continuous integration with GitHub Actions. Every push and pull request triggers a build and full test run on Ubuntu. Test results are uploaded as artifacts.
- Team roles. Each member owned a clear part of the project: diagrams, implementation, testing, and presentation.

## Reflection

The biggest thing we took away was how much the two design patterns changed the way we wrote the code. Our first diagram had `User` and `NoticeBoard` tied directly together, and adding a new notice type meant editing existing classes. Once we introduced the Observer interface and the factory, the calling code stopped depending on the concrete classes and started depending on the interfaces instead. That made the system much easier to extend.

If we did this again we would write more tests earlier. We added most of them near the end of the project, which meant a few small refactors were needed to make the code testable. Writing tests as we went would have caught those issues sooner.
