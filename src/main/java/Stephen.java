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
                history.add(new Task(input));
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }
}
