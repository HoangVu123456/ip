import java.util.*;
import java.io.*;

public class Stephen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Task> history = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Stephen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            try {
                String input = br.readLine();

                if (input == null) {
                    break;
                }

                Command cmd = Command.command(input);

                switch (cmd) {
                case LIST:
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    if (history.isEmpty()) {
                        System.out.println("");
                    } else {
                        for (int i = 0; i < history.size(); i++) {
                            System.out.println((i + 1) + ". " + history.get(i).toString());
                        }
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case MARK:
                    System.out.println("____________________________________________________________");
                    int markIndex = Integer.parseInt(input.substring(5)) - 1;
                    if (markIndex < 0 || markIndex >= history.size()) {
                        throw new InvalidNumberException("Invalid or out of bounds task number. Please enter a value between 1 and " + history.size());
                    }
                    history.get(markIndex).mark();
                    System.out.println("Nice! I've marked this task as done: " + history.get(markIndex).toString());
                    System.out.println("____________________________________________________________");
                    break;
                case UNMARK:
                    System.out.println("____________________________________________________________");
                    int unmarkIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (unmarkIndex < 0 || unmarkIndex >= history.size()) {
                        throw new InvalidNumberException("Invalid or out of bounds task number. Please enter a value between 1 and " + history.size());
                    }
                    history.get(unmarkIndex).unmark();
                    System.out.println("OK, I've marked this task as not done yet: " + history.get(unmarkIndex).toString());
                    System.out.println("____________________________________________________________");
                    break;
                case DELETE:
                    System.out.println("____________________________________________________________");
                    int deleteIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (deleteIndex < 0 || deleteIndex >= history.size()) {
                        throw new InvalidNumberException("Invalid or out of bounds task number. Please enter a value between 1 and " + history.size());
                    }
                    Task removedTask = history.remove(deleteIndex);
                    System.out.println("Noted. I've removed this task: " + removedTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case BYE:
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    br.close();
                    return;
                case TODO:
                    String todoDescription = input.substring(5).trim();
                    if (todoDescription.isEmpty()) {
                        throw new EmptyTaskException("The description for todo task can not be empty! Please try again!");
                    }
                    Task todoTask = new ToDosTask(todoDescription);
                    history.add(todoTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todoTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case DEADLINE:
                    String[] deadlineParts = input.substring(9).split(" /by ");
                    if (deadlineParts.length != 2) {
                        throw new WrongFormatException("Wrong format for deadline task command! Please use format: deadline <description> /by <time>");
                    }
                    String deadlineDescription = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    if (deadlineDescription.isEmpty() || by.isEmpty()) {
                        throw new EmptyTaskException("The command is missing essential information. The description and deadline cannot be empty.");
                    }
                    Task deadlineTask = new DeadlinesTask(deadlineDescription, by);
                    history.add(deadlineTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(deadlineTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case EVENT:
                    String[] eventParts = input.substring(6).split(" /from | /to ");
                    if (eventParts.length != 3) {
                        throw new WrongFormatException("Wrong format for event task command! Please use format: event <description> /from <start> /to <end>");
                    }
                    String eventDescription = eventParts[0].trim();
                    String from = eventParts[1].trim();
                    String to = eventParts[2].trim();
                    if (eventDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new EmptyTaskException("The command is missing essential information. The description, start time, and end time cannot be empty.");
                    }
                    Task eventTask = new EventsTask(eventDescription, from, to);
                    history.add(eventTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(eventTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case UNKNOWN:
                default:
                    throw new InvalidInputException("Invalid input! I'm sorry, but I don't know what that means");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        br.close();
    }
}
