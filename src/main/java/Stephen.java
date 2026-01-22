import java.util.Scanner;

public class Stephen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Stephen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("list");
                System.out.println("____________________________________________________________");
            } else if (input.equals("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blah");
                System.out.println("____________________________________________________________");
            } else if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
        sc.close();
    }
}
