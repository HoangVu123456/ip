# Stephen User Guide

Char char, Charmander! Let's the adorable charmander: Stephen help you organize your tasks!

![Stephen UI](background.jpg)

## Quick Start

1. Ensure Java `17` is installed. If not, you can download it from [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
2. Download the latest `.jar` file from [here](https://github.com/HoangVu123456/ip/releases).
3. Open a terminal, navigate to the folder containing the `.jar` file
4. Run `java -jar stephen.jar` to start the app.
5. Type commands into the input box and press Enter or click Send.
6. Use `help` to see all available commands.

## Features

### View all tasks

Shows all tasks in your list.

Command:

`list`

Example output:

```
Char char!
(Here are the tasks in your list:
1. [T] [ ] Read book
2. [D] [X] Submit (by: Feb 20 2026 09:30)
3. [E] [ ] Meet (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00))
```

### Add a todo

Adds a simple task without a date/time.

Command:

`todo <description>`

Example:

`todo Read book`

Expected output:

```
Char char mander!
(Got it. I've added this task:
 [T] [ ] Read book
Now you have 1 tasks in the list.)
```

### Add a deadline

Adds a task with a due date/time.

Command:

`deadline <description> /by <date/time>`

Examples:

`deadline Submit report /by 20/2/2026 0930`

`deadline Pay rent /by next monday 1800`

Expected output:

```
Char char mander!
(Got it. I've added this task:
 [D] [ ] Submit report (by: Feb 20 2026 09:30)
Now you have 2 tasks in the list.)
```

### Add an event

Adds a task with a start and end date/time.

Command:

`event <description> /from <date/time> /to <date/time>`

Example:

`event Team meeting /from 21/2/2026 1000 /to 21/2/2026 1100`

Expected output:

```
Char char mander!
(Got it. I've added this task:
 [E] [ ] Team meeting (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00)
Now you have 3 tasks in the list.)
```

### Mark a task as done

Command:

`mark <task number>`

Example:

`mark 2`

Expected output:

```
Char mander!
(OK, I've marked this task as done: [D] [X] Submit report (by: Feb 20 2026 09:30))
```

### Unmark a task

Command:

`unmark <task number>`

Example:

`unmark 2`

Expected output:

```
Char char!
(OK, I've marked this task as not done yet: [D] [ ] Submit report (by: Feb 20 2026 09:30))
```

### Delete a task

Command:

`delete <task number>`

Example:

`delete 3`

Expected output:

```
Charmander!
(Noted. I've removed this task: [E] [ ] Team meeting (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00)
Now you have 2 tasks in the list.)
```

### Find tasks

Finds tasks whose description contains the keyword.

Command:

`find <keyword>`

Example:

`find meeting`

Expected output:

```
Char char!
(Here are the matching tasks in your list:
1. [E] [ ] Team meeting (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00))
```

### Help

Shows the command list.

Command:

`help`

Expected output:

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

### Exit

Closes the app.

Command:

`bye`

Expected output:

```
Char! Charmander char char!
(Bye! Hope to see you again soon!)
```

## Notes

- Dates accept formats like `d/M/yyyy HHmm` (e.g., `20/2/2026 0930`) or natural dates like `today`, `tomorrow`, or `next monday`.
- Task numbers are shown by `list` and start from 1.

## Acknowledgements

### Usage of Generative AI Tools
- Copilot helps me choosing the colour for dialog box chat and generate random Charmander chatting message.
- Copilot suggest me which pokemon to used as background image.

Reference(s) used for User Guide:  
https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

Reference(s) used for working with Java:  
https://www.w3schools.com/java/

Reference(s) used for working with Java Fxml:  
https://jenkov.com/tutorials/javafx/fxml.html
