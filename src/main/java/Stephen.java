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

                if (input.equals("list")) {
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
                } else if (input.startsWith("mark ")) {
                    System.out.println("____________________________________________________________");
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index < 0 || index >= history.size()) {
                        throw new InvalidNumberException("Invalid or out of bounds task number. Please enter a value between 1 and " + history.size());
                    }
                    history.get(index).mark();
                    System.out.println("Nice! I've marked this task as done: " + history.get(index).toString());
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark ")) {
                    System.out.println("____________________________________________________________");
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= history.size()) {
                        throw new InvalidNumberException("Invalid or out of bounds task number. Please enter a value between 1 and " + history.size());
                    }
                    history.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet: " + history.get(index).toString());
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete ")) {
                    System.out.println("____________________________________________________________");
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= history.size()) {
                        throw new InvalidNumberException("Invalid or out of bounds task number. Please enter a value between 1 and " + history.size());
                    }
                    Task removedTask = history.remove(index);
                    System.out.println("Noted. I've removed this task: " + removedTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new EmptyTaskException("The description for todo task can not be empty! Please try again!");
                    }
                    Task newTask = new ToDosTask(description);
                    history.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length != 2) {
                        throw new WrongFormatException("Wrong format for deadline task command! Please use format: deadline <description> /by <time>");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new EmptyTaskException("The command is missing essential information. The description and deadline cannot be empty.");
                    }
                    Task newTask = new DeadlinesTask(description, by);
                    history.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length != 3) {
                        throw new WrongFormatException("Wrong format for event task command! Please use format: event <description> /from <start> /to <end>");
                    }
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new EmptyTaskException("The command is missing essential information. The description, start time, and end time cannot be empty.");
                    }
                    Task newTask = new EventsTask(description, from, to);
                    history.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new InvalidInputException("Invalid input! I'm sorry, but I don't know what that means");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        br.close();
    }
}
