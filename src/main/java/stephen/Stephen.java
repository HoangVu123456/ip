package stephen;

import java.util.List;
import java.util.stream.Collectors;

import stephen.exception.EmptyTaskException;
import stephen.exception.InvalidInputException;
import stephen.exception.InvalidNumberException;
import stephen.exception.WrongFormatException;

/**
 * Represents the Stephen chatbot.
 */
public class Stephen {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private boolean isLastResponseError;

    /**
     * Constructor for Stephen chatbot.
     */
    public Stephen() {
        storage = new Storage();
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Get response from Stephen chatbot.
     */
    public String getResponse(String input) {
        isLastResponseError = false;
        try {
            String normalized = input.trim().replaceAll("\\s+", " ");
            Command cmd = parser.parse(normalized);
            switch (cmd) {
            case LIST:
                return getListString();
            case MARK:
                return getMarkString(normalized);
            case UNMARK:
                return getUnmarkString(normalized);
            case DELETE:
                return getDeleteString(normalized);
            case BYE:
                return "Char! Charmander char char! 🔥\n(Bye! Hope to see you again soon!)";
            case TODO:
                return getTodoString(normalized);
            case DEADLINE:
                return getDeadlineString(normalized);
            case EVENT:
                return getEventString(normalized);
            case FIND:
                return getFindString(normalized);
            case HELP:
                return getHelpString();
            case UNKNOWN:
            default:
                throw new InvalidInputException(
                    "Char, char? Charmander! 🔥\n(I don't understand what you mean! \nType \"help\" for a list of commands)"
                );
            }
        } catch (Exception e) {
            isLastResponseError = true;
            return e.getMessage();
        }
    }

    public boolean isLastResponseError() {
        return isLastResponseError;
    }
    /**
     * Gets the string representation to the response for help command.
     */
    private String getHelpString() {
        return "Char char! Charmander! 🔥\n"
                + "(Here's what I can do for you!)\n\n"
                + " 1. list - View all your tasks\n"
                + " 2. mark <number> - Mark a task\n"
                + " 3. unmark <number> - Unmark a task\n"
                + " 4. delete <number> - Remove a task\n"
                + " 5. todo <description> - Add a todo task\n"
                + " 6. deadline <description> /by <date/time> - Add a deadline task\n"
                + " 7. event <description> /from <date/time> /to <date/time> - Add an event task\n"
                + " 8. find <keyword> - Search for tasks with a keyword\n"
                + " 9. help - Show the help message\n"
                + " 10. bye - Exit the app\n\n"
                + "Char! 🔥 (Let's catch 'em all!)";
    }
    /**
     * Gets the string representation to the response for list command.
     */
    private String getListString() {
        StringBuilder sb = new StringBuilder("Char char! 🔥\n(Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            sb.append("\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("\n").append(i + 1).append(". ").append(tasks.getTask(i).toString());
            }
        }
        return sb.toString() + ")";
    }
    /**
     * Gets the string representation to the response for mark command.
     */
    private String getMarkString(String input) {
        int markIndex = Integer.parseInt(input.substring(5)) - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Char char! Mander mander! 🔥\n(That task number doesn't exist! Pick between 1 and "
                        + tasks.size() + ")");
        }
        tasks.getTask(markIndex).mark();
        storage.save(tasks.getTasks());
            return "Char mander! 🔥\n(OK, I've marked this task as done: " + tasks.getTask(markIndex).toString() + ")";
    }
    /**
     * Gets the string representation to the response for unmark command.
     */
    private String getUnmarkString(String input) {
        int unmarkIndex = Integer.parseInt(input.substring(7)) - 1;
        if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Char char! Mander mander! 🔥\n(That task number doesn't exist! Pick between 1 and "
                        + tasks.size() + ")");
        }
        tasks.getTask(unmarkIndex).unmark();
        storage.save(tasks.getTasks());
        return "Char char! 🔥\n(OK, I've marked this task as not done yet: " + tasks.getTask(unmarkIndex).toString() + ")";
    }
    /**
     * Gets the string representation to the response for delete command.
     */
    private String getDeleteString(String input) {
        int deleteIndex = Integer.parseInt(input.substring(7)) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Char char! Mander mander! 🔥\n(That task number doesn't exist! Pick between 1 and "
                        + tasks.size() + ")");
        }
        Task removedTask = tasks.deleteTask(deleteIndex);
        storage.save(tasks.getTasks());
        return "Charmander! 🔥\n(Noted. I've removed this task: " + removedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.)";
    }
    /**
     * Gets the string representation to the response for todo command.
     */
    private String getTodoString(String input) {
        String todoDescription = input.substring(5).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyTaskException(
                "Char! Charmander char! 🔥\n(You didn't tell me what the todo is!)"
            );
        }
        Task todoTask = new ToDosTask(todoDescription);
        tasks.addTask(todoTask);
        storage.save(tasks.getTasks());
        return "Char char mander! 🔥\n(Got it. I've added this task:\n " + todoTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.)";
    }
    /**
     * Gets the string representation to the response for deadline command.
     */
    private String getDeadlineString(String input) {
        String[] deadlineParts = input.substring(9).split(" /by ");
        if (deadlineParts.length != 2) {
            throw new WrongFormatException(
                "Char char mander! 🔥\n(Wrong format! Use: deadline <description> /by <time>)"
            );
        }
        String deadlineDescription = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        if (deadlineDescription.isEmpty() || by.isEmpty()) {
            throw new EmptyTaskException(
                "Charmander char char! 🔥\n(You're missing the description or deadline!)"
            );
        }
        Task deadlineTask = new DeadlinesTask(deadlineDescription, by);
        tasks.addTask(deadlineTask);
        storage.save(tasks.getTasks());
        return "Char char mander! 🔥\n(Got it. I've added this task:\n " + deadlineTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.)";
    }
    /**
     * Gets the string representation to the response for event command.
     */
    private String getEventString(String input) {
        String[] eventParts = input.substring(6).split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new WrongFormatException(
                "Char char mander! 🔥\n(Wrong format! Use: event <description> /from <start> /to <end>)"
            );
        }
        String eventDescription = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();
        if (eventDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EmptyTaskException(
                "Charmander char char! 🔥\n(You're missing some info! Need description, start time, and end time!)"
            );
        }
        Task eventTask = new EventsTask(eventDescription, from, to);
        tasks.addTask(eventTask);
        storage.save(tasks.getTasks());
        return "Char char mander! 🔥\n(Got it. I've added this task:\n " + eventTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.)";
    }
    /**
     * Gets the string representation to the response for find command.
     */
    private String getFindString(String input) {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new EmptyTaskException(
                "Char char! Mander! 🔥\n(You need to tell me what to search for!)"
            );
        }
        List<Task> matches = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder("Char char! 🔥\n(Here are the matching tasks in your list:");
        if (matches.isEmpty()) {
            sb.append("\nMander char! (You have no matching tasks in your list.)");
        } else {
            for (int i = 0; i < matches.size(); i++) {
                sb.append("\n").append(i + 1).append(". ").append(matches.get(i).toString());
            }
        }
        return sb.toString() + ")";
    }
}
