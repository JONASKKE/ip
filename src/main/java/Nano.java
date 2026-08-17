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

            } else {
                tasks[taskCount] = new Task(task);
                taskCount++;

                System.out.println("added: " + task);
            }
        }

        scanner.close();
    }
}