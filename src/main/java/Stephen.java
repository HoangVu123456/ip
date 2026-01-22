import java.util.*;

public class Stephen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> history = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Stephen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine().trim();

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
                if (index >= 0 && index < history.size()) {
                    history.get(index).mark();
                    System.out.println("Nice! I've marked this task as done: " + history.get(index).toString());
                } else {
                    System.out.println("Invalid task number");
                }
            } else if (input.startsWith("unmark ")) {
                System.out.println("____________________________________________________________");
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < history.size()) {
                    history.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet: " + history.get(index).toString());
                } else {
                    System.out.println("Invalid task number");
                }
            } else if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    Task newTask = new ToDosTask(description);
                    history.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + history.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length == 2) {
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        Task newTask = new DeadlinesTask(description, by);
                        history.add(newTask);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newTask.toString());
                        System.out.println("Now you have " + history.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length == 3) {
                        String description = parts[0].trim();
                        String from = parts[1].trim();
                        String to = parts[2].trim();
                        Task newTask = new EventsTask(description, from, to);
                        history.add(newTask);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newTask.toString());
                        System.out.println("Now you have " + history.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                }
            }
        }
        sc.close();
    }
}
