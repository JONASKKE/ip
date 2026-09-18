---
title: Nano User Guide
description: A practical guide to using the Nano task-management chatbot.
---

<div align="center">

<h1>✦ NANO ✦</h1>

<h3>Your calm, command-line companion for getting things done.</h3>

<p>
  <img src="https://img.shields.io/badge/Java-25-5865D9?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 25">
  <img src="https://img.shields.io/badge/JavaFX-GUI-AEBFF0?style=for-the-badge" alt="JavaFX GUI">
  <img src="https://img.shields.io/badge/status-student%20project-DCE6FF?style=for-the-badge" alt="Student project">
</p>

<p><strong>Plan less. Finish more.</strong></p>

</div>

## Meet Nano

Nano is a lightweight task-management chatbot with a friendly JavaFX interface. Type natural commands into the message box to organise your tasks, deadlines, events, and priorities.

| ✨ What Nano does | 🎯 Why it helps |
| --- | --- |
| Adds todos, deadlines, and events | Capture tasks quickly |
| Lists and searches tasks | Find what matters |
| Tracks completion and priority | Keep work organised |
| Saves tasks automatically | Continue where you left off |

## Start here

1. Open the project in IntelliJ IDEA.
2. Configure the project to use **JDK 25**.
3. Run `src/main/java/nano/Launcher.java`.
4. Type a command and click **Send** or press **Enter**.

Nano saves your tasks automatically in `data/nano.txt`.

## Your first three commands

```text
todo read a book
list
mark 1
```

That creates a task, displays your task list, and marks task 1 as completed.

## Add tasks

<details>
<summary><strong>To-do</strong> — a task without a date</summary>

```text
todo read a book
```

</details>

<details>
<summary><strong>Deadline</strong> — a task due at a specific time</summary>

Use the format `yyyy-MM-dd HHmm` after `/by`.

```text
deadline submit assignment /by 2026-10-01 1800
```

</details>

<details>
<summary><strong>Event</strong> — something with a start and end time</summary>

Use `/from` and `/to` with the format `yyyy-MM-dd HHmm`.

```text
event project meeting /from 2026-10-02 1400 /to 2026-10-02 1530
```

</details>

## View and search

See every saved task:

```text
list
```

Search descriptions with a case-insensitive keyword:

```text
find assignment
```

## Update your list

| Action | Example |
| --- | --- |
| Complete a task | `mark 1` |
| Reopen a task | `unmark 1` |
| Set priority | `priority 1 high` |
| Remove a task | `delete 1` |

Priority values are `high`, `normal`, and `low`.

## Command deck

| Command | Purpose |
| --- | --- |
| `todo <description>` | Add a to-do |
| `deadline <description> /by <date and time>` | Add a deadline |
| `event <description> /from <start> /to <end>` | Add an event |
| `list` | Show all tasks |
| `find <keyword>` | Search for matching tasks |
| `mark <task number>` | Mark a task complete |
| `unmark <task number>` | Mark a task unfinished |
| `priority <task number> <high\|normal\|low>` | Change priority |
| `delete <task number>` | Delete a task |
| `bye` | Show the goodbye banner and exit |

## Signing off

When your work is done, type:

```text
bye
```

Nano displays its goodbye banner and closes after a short delay.

## Quick fixes

- Use lowercase commands.
- Use the task number shown by `list` for `mark`, `unmark`, `priority`, and `delete`.
- Check the date format for deadlines and events.
- If Nano rejects a command, read the response and adjust the syntax.

<div align="center">

### Built with Java · Designed for focus · Powered by Nano

</div>
