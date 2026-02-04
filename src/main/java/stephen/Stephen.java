package stephen;

import stephen.exception.EmptyTaskException;
import stephen.exception.InvalidInputException;
import stephen.exception.InvalidNumberException;
import stephen.exception.WrongFormatException;

/**
 * Runs the Stephen chatbot.
 */
public class Stephen {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Stephen chatbot.
     */
    public Stephen() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();

                if (input == null) {
                    break;
                }

                Command cmd = parser.parse(input);

                switch (cmd) {
                case LIST:
                    handleList();
                    break;
                case MARK:
                    handleMark(input);
                    break;
                case UNMARK:
                    handleUnmark(input);
                    break;
                case DELETE:
                    handleDelete(input);
                    break;
                case BYE:
                    ui.showGoodbye();
                    ui.close();
                    isExit = true;
                    break;
                case TODO:
                    handleToDo(input);
                    break;
                case DEADLINE:
                    handleDeadline(input);
                    break;
                case EVENT:
                    handleEvent(input);
                    break;
                case FIND:
                    handleFind(input);
                    break;
                case UNKNOWN:
                default:
                    throw new InvalidInputException(
                        "Invalid input! I'm sorry, but I don't know what that means"
                    );
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Handles the 'list' command.
     */
    private void handleList() {
        ui.showLine();
        ui.println("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            ui.println("");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.println((i + 1) + ". " + tasks.getTask(i).toString());
            }
        }
        ui.showLine();
    }

    /**
     * Handles the 'mark' command.
     */
    private void handleMark(String input) {
        ui.showLine();
        int markIndex = Integer.parseInt(input.substring(5)) - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Invalid or out of bounds task number. Please enter a value between 1 and "
                        + tasks.size());
        }
        tasks.getTask(markIndex).mark();
        ui.println("Nice! I've marked this task as done: " + tasks.getTask(markIndex).toString());
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the 'unmark' command.
     */
    private void handleUnmark(String input) {
        ui.showLine();
        int unmarkIndex = Integer.parseInt(input.substring(7)) - 1;
        if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Invalid or out of bounds task number. Please enter a value between 1 and "
                        + tasks.size());
        }
        tasks.getTask(unmarkIndex).unmark();
        ui.println("OK, I've marked this task as not done yet: " + tasks.getTask(unmarkIndex).toString());
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the 'delete' command.
     */
    private void handleDelete(String input) {
        ui.showLine();
        int deleteIndex = Integer.parseInt(input.substring(7)) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new InvalidNumberException(
                "Invalid or out of bounds task number. Please enter a value between 1 and "
                        + tasks.size());
        }
        Task removedTask = tasks.deleteTask(deleteIndex);
        ui.println("Noted. I've removed this task: " + removedTask.toString());
        ui.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the 'todo' command.
     */
    private void handleToDo(String input) {
        String todoDescription = input.substring(5).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyTaskException(
                "The description for todo task can not be empty! Please try again!"
            );
        }
        Task todoTask = new ToDosTask(todoDescription);
        tasks.addTask(todoTask);
        ui.showLine();
        ui.println("Got it. I've added this task: ");
        ui.println(todoTask.toString());
        ui.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the 'deadline' command.
     */
    private void handleDeadline(String input) {
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
        ui.showLine();
        ui.println("Got it. I've added this task: ");
        ui.println(deadlineTask.toString());
        ui.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the 'event' command.
     */
    private void handleEvent(String input) {
        String[] eventParts = input.substring(6).split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new WrongFormatException(
                "Wrong format for event task command! "
                        + "Please use format: event <description> /from <start> /to <end>"
            );
        }
        String eventDescription = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();
        if (eventDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EmptyTaskException(
                "The command is missing essential information. "
                        + "The description, start time, and end time cannot be empty."
            );
        }
        Task eventTask = new EventsTask(eventDescription, from, to);
        tasks.addTask(eventTask);
        ui.showLine();
        ui.println("Got it. I've added this task: ");
        ui.println(eventTask.toString());
        ui.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the find command
     */
    private void handleFind(String input) {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new EmptyTaskException(
                "The search keyword cannot be empty! Please provide a keyword."
            );
        }
        ui.showLine();
        ui.println("Here are the matching tasks in your list:");
        int matchCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDesScription().contains(keyword)) {
                matchCount++;
                ui.println(matchCount + "." + task.toString());
            }
        }
        if (matchCount == 0) {
            ui.println("You have no matching tasks in your list.");
        }
        ui.showLine();
    }

    /**
     * Runs the whole program.
     */
    public static void main(String[] args) {
        Stephen stephen = new Stephen();
        stephen.run();
    }
}
