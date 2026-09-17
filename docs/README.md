# Nano User Guide

Nano is a simple task-management chatbot with a JavaFX graphical user interface. You can add, view, search, update, and delete tasks using commands typed into the message box.

## Getting started

1. Open the project in IntelliJ IDEA.
2. Configure the project to use JDK 25.
3. Run `src/main/java/nano/Launcher.java`.
4. Nano opens with a welcome message. Type a command and click **Send** or press **Enter**.

Nano automatically saves tasks in `data/nano.txt`, so they are available the next time you start the application.

## Adding tasks

### To-do tasks

```text
todo read a book
```

### Deadlines

Use `/by` with the format `yyyy-MM-dd HHmm`:

```text
deadline submit assignment /by 2026-10-01 1800
```

### Events

Use `/from` and `/to` for the start and end times:

```text
event project meeting /from 2026-10-02 1400 /to 2026-10-02 1530
```

## Viewing and searching tasks

Use `list` to display every saved task:

```text
list
```

Use `find <keyword>` to search for matching tasks. Searches are case-insensitive.

## Updating tasks

```text
mark 1
unmark 1
priority 1 high
```

These commands mark task 1 as completed, mark it unfinished, or set its priority. Priority can be `high`, `normal`, or `low`.

## Deleting tasks

Delete a task by its number:

```text
delete 1
```

Nano confirms the deleted task and shows how many tasks remain.

## Exiting Nano

Type `bye` to display Nano's goodbye banner and close the application after a short delay.

## Command summary

| Command | Purpose |
| --- | --- |
| `todo <description>` | Add a to-do task |
| `deadline <description> /by <date and time>` | Add a deadline |
| `event <description> /from <start> /to <end>` | Add an event |
| `list` | Show all tasks |
| `find <keyword>` | Search for matching tasks |
| `mark <task number>` | Mark a task completed |
| `unmark <task number>` | Mark a task unfinished |
| `priority <task number> <high\|normal\|low>` | Change priority |
| `delete <task number>` | Delete a task |
| `bye` | Display the goodbye message and exit |

## Troubleshooting

- Write commands in lowercase.
- Use the task number shown by `list` for update and delete commands.
- Check the date format for deadlines and events.
- If a command is invalid, Nano displays an explanatory error message.
