import java.util.Scanner;

public class Nano {
    public static void main(String[] args) {
        String banner = "NN   NN   AAA   NN   NN   OOO \n"
                + "NNN  NN  AA AA  NNN  NN  OO OO\n"
                + "NN N NN AA   AA NN N NN OO  OO\n"
                + "NN  NNN AAAAAAA NN  NNN OO  OO\n"
                + "NN   NN AA   AA NN   NN  OOO \n";
        System.out.println(banner);
        System.out.println("Hello! I'm Nano.");
        System.out.println("How may i assist you?");

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String task = scanner.nextLine();

            if (task.equals("bye")) {
                System.out.println(
                        "██████╗ ██╗   ██╗███████╗\n" +
                                "██╔══██╗╚██╗ ██╔╝██╔════╝\n" +
                                "██████╔╝ ╚████╔╝ █████╗  \n" +
                                "██╔══██╗  ╚██╔╝  ██╔══╝  \n" +
                                "██████╔╝   ██║   ███████╗\n" +
                                "╚═════╝    ╚═╝   ╚══════╝"
                );
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (task.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }

            } else if (task.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(task.substring(5));
                Task t = tasks[taskNumber - 1];

                t.markDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + t);

            } else if (task.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(task.substring(7));
                Task t = tasks[taskNumber - 1];

                t.markUndone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + t);

            } else if (task.startsWith("todo ")) {
                String description = task.substring(5);

                tasks[taskCount] = new Todo(description);
                taskCount++;

                System.out.println("Got it. Adding todo:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else if (task.startsWith("deadline ")) {
                String input = task.substring(9);

                int separator = input.indexOf(" /by ");

                String description = input.substring(0, separator);
                String by = input.substring(separator + 5);

                tasks[taskCount] = new Deadline(description, by);
                taskCount++;

                System.out.println("Got it. Adding deadline:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else if (task.startsWith("event ")) {
                String input = task.substring(6);

                int fromIndex = input.indexOf(" /from ");
                int toIndex = input.indexOf(" /to ");

                String description = input.substring(0, fromIndex).trim();
                String from = input.substring(fromIndex + 7, toIndex).trim();
                String to = input.substring(toIndex + 4).trim();

                tasks[taskCount] = new Event(description, from, to);
                taskCount++;

                System.out.println("Got it. Adding event:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");

            } else {
                tasks[taskCount] = new Task(task);
                taskCount++;

                System.out.println("added: " + task);
            }
        }

        scanner.close();
    }
}