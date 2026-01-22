import java.util.*;

public class Stephen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> history = new ArrayList<>();

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
                        System.out.println((i + 1) + ". " + history.get(i));
                    }
                }
                System.out.println("____________________________________________________________");
            } else if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                history.add(input);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }
}
