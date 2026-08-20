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

            try {
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

                } else if (task.startsWith("mark ") || task.equals("mark")) {
                    int taskNumber;

                    try {
                        taskNumber = Integer.parseInt(task.substring(4).trim());
                    } catch (NumberFormatException e) {
                        throw new NanoException("The task number must be a number.");
                    }

                    if (taskNumber < 1 || taskNumber > taskCount) {
                        throw new NanoException("That task number does not exist.");
                    }

                    Task t = tasks[taskNumber - 1];

                    t.markDone();

                    System.out.println("Keep up the good work! I've marked this task as done:");
                    System.out.println("  " + t);

                } else if (task.startsWith("unmark ")  || task.equals("unmark")) {
                    int taskNumber;

                    try {
                        taskNumber = Integer.parseInt(task.substring(6).trim());
                    } catch (NumberFormatException e) {
                        throw new NanoException("The task number must be a number.");
                    }

                    if (taskNumber < 1 || taskNumber > taskCount) {
                        throw new NanoException("That task number does not exist.");
                    }

                    Task t = tasks[taskNumber - 1];

                    t.markUndone();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + t);

                } else if (task.startsWith("todo ") || task.equals("todo")) {
                    String description = task.substring(4).trim();

                    tasks[taskCount] = new Todo(description);
                    taskCount++;

                    System.out.println("Got it. Adding todo:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");

                } else if (task.startsWith("deadline ") || task.equals("deadline")) {
                    String input = task.substring(8).trim();

                    int separator = input.indexOf("/by");

                    String description;
                    String by;

                    if (separator == -1) {
                        description = input;
                        by = "";
                    } else {
                        description = input.substring(0, separator).trim();
                        by = input.substring(separator + 3).trim();
                    }

                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;

                    System.out.println("Got it. Adding deadline:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");

                } else if (task.startsWith("event ") || task.equals("event")) {
                    String input = task.substring(5).trim();

                    int fromIndex = input.indexOf("/from ");
                    int toIndex = input.indexOf("/to ");

                    String description;
                    String from;
                    String to;

                    if (fromIndex == -1) {
                        description = input;
                        from = "";
                        to = "";
                    } else {
                        description = input.substring(0, fromIndex).trim();

                        if (toIndex == -1) {
                            from = input.substring(fromIndex + 5).trim();
                            to = "";
                        } else {
                            from = input.substring(fromIndex + 5, toIndex).trim();
                            to = input.substring(toIndex + 3).trim();
                        }
                    }

                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;

                    System.out.println("Got it. Adding event:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");

                } else {
                    throw new NanoException("What do you mean?");
                }
            } catch (NanoException e) {
                System.out.println("HUH?! " + e.getMessage());
            }
        }
        scanner.close();
    }
}