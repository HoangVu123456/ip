package stephen;

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
        try {
            Command cmd = parser.parse(input);
            switch (cmd) {
            case LIST:
                return getListString();
            case MARK:
                return getMarkString(input);
            case UNMARK:
                return getUnmarkString(input);
            case DELETE:
                return getDeleteString(input);
            case BYE:
                return "Bye. Hope to see you again soon!";
            case TODO:
                return getTodoString(input);
            case DEADLINE:
                return getDeadlineString(input);
            case EVENT:
                return getEventString(input);
            case FIND:
                return getFindString(input);
            case UNKNOWN:
            default:
                throw new InvalidInputException(
                    "Invalid input! I'm sorry, but I don't know what that means"
                );
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    /**
     * Gets the string representation to the response for list command.
     */
    private String getListString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            sb.append("\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("\n").append(i + 1).append(". ").append(tasks.getTask(i).toString());
            }
        }
        return sb.toString();
    }
    /**
     * Gets the string representation to the response for mark command.
     */
    private String getMarkString(String input) {
        int markIndex = Integer.parseInt(input.substring(5)) - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Invalid or out of bounds task number. Please enter a value between 1 and "
                        + tasks.size());
        }
        tasks.getTask(markIndex).mark();
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done: " + tasks.getTask(markIndex).toString();
    }
    /**
     * Gets the string representation to the response for unmark command.
     */
    private String getUnmarkString(String input) {
        int unmarkIndex = Integer.parseInt(input.substring(7)) - 1;
        if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Invalid or out of bounds task number. Please enter a value between 1 and "
                        + tasks.size());
        }
        tasks.getTask(unmarkIndex).unmark();
        storage.save(tasks.getTasks());
        return "OK, I've marked this task as not done yet: " + tasks.getTask(unmarkIndex).toString();
    }
    /**
     * Gets the string representation to the response for delete command.
     */
    private String getDeleteString(String input) {
        int deleteIndex = Integer.parseInt(input.substring(7)) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Invalid or out of bounds task number. Please enter a value between 1 and "
                        + tasks.size());
        }
        Task removedTask = tasks.deleteTask(deleteIndex);
        storage.save(tasks.getTasks());
        return "Noted. I've removed this task: " + removedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    /**
     * Gets the string representation to the response for todo command.
     */
    private String getTodoString(String input) {
        String todoDescription = input.substring(5).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyTaskException(
                "The description for todo task can not be empty! Please try again!"
            );
        }
        Task todoTask = new ToDosTask(todoDescription);
        tasks.addTask(todoTask);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n " + todoTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    /**
     * Gets the string representation to the response for deadline command.
     */
    private String getDeadlineString(String input) {
        String[] deadlineParts = input.substring(9).split(" /by ");
        if (deadlineParts.length != 2) {
            throw new WrongFormatException(
                "Wrong format for deadline task command! Please use format: deadline <description> /by <time>"
            );
        }
        String deadlineDescription = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        if (deadlineDescription.isEmpty() || by.isEmpty()) {
            throw new EmptyTaskException(
                "The command is missing essential information. The description and deadline cannot be empty."
            );
        }
        Task deadlineTask = new DeadlinesTask(deadlineDescription, by);
        tasks.addTask(deadlineTask);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n " + deadlineTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    /**
     * Gets the string representation to the response for event command.
     */
    private String getEventString(String input) {
        String[] eventParts = input.substring(6).split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new WrongFormatException(
                "Wrong format for event task command! Please use format: event <description> /from <start> /to <end>"
            );
        }
        String eventDescription = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();
        if (eventDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EmptyTaskException(
                "The command is missing essential information."
                        + " The description, start time, and end time cannot be empty."
            );
        }
        Task eventTask = new EventsTask(eventDescription, from, to);
        tasks.addTask(eventTask);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n " + eventTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    /**
     * Gets the string representation to the response for find command.
     */
    private String getFindString(String input) {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new EmptyTaskException(
                "The search keyword cannot be empty! Please provide a keyword."
            );
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        int matchCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchCount++;
                sb.append("\n").append(matchCount).append(". ").append(task.toString());
            }
        }
        if (matchCount == 0) {
            sb.append("\nYou have no matching tasks in your list.");
        }
        return sb.toString();
    }
}
