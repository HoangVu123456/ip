# Stephen User Guide

Char char, Charmander! Let's have the adorable Charmander, Stephen, help you organize your tasks!

![Stephen UI](background.jpg)

## 📚 Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
- [Date/Time Formats](#datetime-formats)
- [Command Reference](#command-reference)
- [Tips and Tricks](#tips-and-tricks)
- [Troubleshooting](#troubleshooting)
- [Acknowledgements](#acknowledgements)

## Quick Start

Getting started with Stephen is easy!

### Prerequisites
- Java 17 or later installed on your system. [Download Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### Installation & Launch
1. Download the latest `stephen.jar` file from [Releases](https://github.com/HoangVu123456/ip/releases)
2. Open a terminal/command prompt and navigate to the folder containing the `.jar` file
3. Run: `java -jar stephen.jar`
4. The Stephen chatbot window will open
5. Type your commands into the input box and press Enter or click Send
6. Use the `help` command to see all available commands at any time

### First Steps
- Type `help` to see a list of all commands
- Try `list` to see if you have any existing tasks
- Add a task with `todo Read book`
- View your tasks again with `list`

## Features

### ✅ View All Tasks

Displays all your tasks with their status and details.

**Command:**
```
list
```

**Example Output:**
```
Char char!
(Here are the tasks in your list:
1. [T] [ ] Read book
2. [D] [X] Submit report (by: Feb 20 2026 09:30)
3. [E] [ ] Team meeting (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00))
```

**Task Format:**
- `[T]` = Todo (simple task with no deadline)
- `[D]` = Deadline (task with a due date)
- `[E]` = Event (task with start and end times)
- `[ ]` = Not done
- `[X]` = Done

---

### ➕ Add a Todo

Adds a simple task without a specific date or deadline, perfect for general tasks.

**Command:**
```
todo <description>
```

**Example:**
```
todo Read book
todo Buy groceries
```

**Expected Output:**
```
Char char mander!
(Got it. I've added this task:
 [T] [ ] Read book
Now you have 1 tasks in the list.)
```

---

### 📅 Add a Deadline

Adds a task with a specific due date and/or time. Useful for assignments, bills, and time-sensitive tasks.

**Command:**
```
deadline <description> /by <date/time>
```

**Examples:**
```
deadline Submit report /by 20/2/2026 0930
deadline Pay rent /by next monday 1800
deadline Project submission /by tomorrow
```

**Expected Output:**
```
Char char mander!
(Got it. I've added this task:
 [D] [ ] Submit report (by: Feb 20 2026 09:30)
Now you have 2 tasks in the list.)
```

---

### 🎪 Add an Event

Adds a task with both a start and end date/time, perfect for meetings, conferences, or appointments.

**Command:**
```
event <description> /from <date/time> /to <date/time>
```

**Example:**
```
event Team meeting /from 21/2/2026 1000 /to 21/2/2026 1100
event Conference /from next friday 0900 /to next friday 1700
```

**Expected Output:**
```
Char char mander!
(Got it. I've added this task:
 [E] [ ] Team meeting (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00)
Now you have 3 tasks in the list.)
```

---

### ✔️ Mark a Task as Done

Marks a task as completed.

**Command:**
```
mark <task number>
```

**Example:**
```
mark 2
```

**Expected Output:**
```
Char mander!
(OK, I've marked this task as done: [D] [X] Submit report (by: Feb 20 2026 09:30))
```

---

### ↩️ Unmark a Task

Marks a previously completed task as not done.

**Command:**
```
unmark <task number>
```

**Example:**
```
unmark 2
```

**Expected Output:**
```
Char char!
(OK, I've marked this task as not done yet: [D] [ ] Submit report (by: Feb 20 2026 09:30))
```

---

### 🗑️ Delete a Task

Removes a task from your list permanently.

**Command:**
```
delete <task number>
```

**Example:**
```
delete 3
```

**Expected Output:**
```
Charmander!
(Noted. I've removed this task: [E] [ ] Team meeting (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00)
Now you have 2 tasks in the list.)
```

---

### 🔍 Find Tasks

Searches for tasks by keyword. Case-sensitive matching on task descriptions.

**Command:**
```
find <keyword>
```

**Example:**
```
find meeting
find report
```

**Expected Output:**
```
Char char!
(Here are the matching tasks in your list:
1. [E] [ ] Team meeting (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00))
```

---

### ℹ️ Help

Displays all available commands and their usage.

**Command:**
```
help
```

**Expected Output:**
```
Char char! Charmander!
(Here's what I can do for you!)

 1. list - View all your tasks
 2. mark <number> - Mark a task
 3. unmark <number> - Unmark a task
 4. delete <number> - Remove a task
 5. todo <description> - Add a todo task
 6. deadline <description> /by <date/time> - Add a deadline task
 7. event <description> /from <date/time> /to <date/time> - Add an event task
 8. find <keyword> - Search for tasks with a keyword
 9. help - Show the help message
 10. bye - Exit the app

Char! (Let's catch 'em all!)
```

---

### 👋 Exit

Closes the application and saves all your tasks automatically.

**Command:**
```
bye
```

**Expected Output:**
```
Char! Charmander char char!
(Bye! Hope to see you again soon!)
```

## Date/Time Formats

Stephen supports flexible date/time input formats to make task creation easier.

### Standard Format
- **Format:** `d/M/yyyy HHmm`
- **Examples:** `20/2/2026 0930`, `1/1/2026 1400`
- **Note:** Time is in 24-hour format

### Natural Date Format
Stephen can parse natural language dates:
- **Today/Tomorrow:** `today`, `tomorrow`
- **Day Names:** `Monday`, `Tuesday`, `wednesday`, `FRIDAY` (case-insensitive)
- **Abbreviated:** `Mon`, `Tue`, `Wed`, `Thu`, `Fri`, `Sat`, `Sun`
- **Relative:** `next monday`, `next friday`

### Time Format
- **24-hour format:** `0930` (9:30 AM) or `1800` (6:00 PM)
- **With colon:** `09:30`, `18:00`
- **Default:** Midnight (00:00) if no time is specified

### Examples
```
deadline Pay rent /by next monday 1800
deadline Follow up /by tomorrow
event Meeting /from today 1400 /to today 1500
deadline Submission /by 20/2/2026 1159
```

## Tips and Tricks

1. **Quick Navigation:** Assign task numbers for easier marking/deletion
2. **Search Efficiently:** Use `find` with partial keywords (e.g., `find book` finds "Read book", "Bookshelf audit")
3. **Batch Operations:** Add multiple similar tasks and mark them together
4. **Date Flexibility:** Mix standard and natural formats (e.g., `21/2/2026 0900` or `next friday 0900`)
5. **Save Automatically:** All operations are automatically saved to your local storage

## Troubleshooting

### Common Issues

**Issue: "I don't understand" error**
- **Solution:** Check the command syntax. Use `help` to see correct formats
- **Example:** `deadline Submit /by 20/2/2026` (correct) not `deadline Submit 20/2/2026`

**Issue: Task number doesn't exist**
- **Solution:** Use `list` to see valid task numbers, then try again
- **Example:** If you have 3 tasks, valid numbers are 1, 2, 3

**Issue: Application won't start**
- **Solution:** Ensure Java 17+ is installed. Run `java -version` to check
- **Alternative:** Download and reinstall the latest `.jar` file

**Issue: Date format not recognized**
- **Solution:** Use standard format `d/M/yyyy HHmm` or natural dates like `today`, `next monday`
- **Example:** `20/2/2026 0930` (correct) not `2026-02-20 09:30`

### Getting Help

- Type `help` in Stephen to see all commands
- Refer to the [Features](#features) section above for detailed command documentation
- Check file storage at `./data/chat_history_data.txt` to verify task data is saved

## Acknowledgements

### Code Quality Enhancements
- AI-assisted code improvements for better documentation, error handling, and maintainability
- Java best practices applied including try-with-resources, improved exception handling

### Usage of Generative AI Tools
- GitHub Copilot: Assisted with choosing UI colors for the dialog box and generating Charmander chat messages
- GitHub Copilot: Suggested Pokemon theme and background image selection

### References

**Java Development:**
- [Java 17 Documentation](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [W3Schools Java Tutorial](https://www.w3schools.com/java/)

**JavaFX UI Development:**
- [Jenkov JavaFX FXML Tutorial](https://jenkov.com/tutorials/javafx/fxml.html)
- [Oracle JavaFX Documentation](https://docs.oracle.com/javase/8/javafx/get-started-tutorial/)
