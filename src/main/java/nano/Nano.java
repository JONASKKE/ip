package nano;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the Nano chatbot and handles user commands.
 */
public class Nano {
    /**
     * Starts the Nano chatbot and processes user commands.
     *
     * @param args command-line arguments.
     * @throws NanoException if an error occurs while loading or saving tasks.
     */
    private static final String DATA_FILE = "./data/nano.txt";

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

        Storage storage = new Storage(DATA_FILE);
        ArrayList<Task> tasks;

        try {
            tasks = storage.load();
        } catch (NanoException e) {
            System.out.println("Oops! " + e.getMessage());
            tasks = new ArrayList<>();
        }

        while (true) {
            String command = scanner.nextLine();

            try {
                if (command.equals("bye")) {
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

                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }

                } else if (command.startsWith("mark ") || command.equals("mark")) {
                    int taskNumber;

                    try {
                        taskNumber = Integer.parseInt(command.substring(4).trim());
                    } catch (NumberFormatException e) {
                        throw new NanoException("The task number must be a number.");
                    }

                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new NanoException("That task number does not exist.");
                    }

                    Task t = tasks.get(taskNumber - 1);

                    t.markDone();
                    storage.save(tasks);

                    System.out.println("Keep up the good work! I've marked this task as done:");
                    System.out.println("  " + t);

                } else if (command.startsWith("unmark ") || command.equals("unmark")) {
                    int taskNumber;

                    try {
                        taskNumber = Integer.parseInt(command.substring(6).trim());
                    } catch (NumberFormatException e) {
                        throw new NanoException("The task number must be a number.");
                    }

                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new NanoException("That task number does not exist.");
                    }

                    Task t = tasks.get(taskNumber - 1);

                    t.markUndone();
                    storage.save(tasks);

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + t);

                } else if (command.startsWith("delete ") || command.equals("delete")) {
                    int taskNumber;

                    try {
                        taskNumber = Integer.parseInt(command.substring(6).trim());
                    } catch (NumberFormatException e) {
                        throw new NanoException("The task number must be a number.");
                    }

                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new NanoException("That task number does not exist.");
                    }

                    Task deletedTask = tasks.remove(taskNumber - 1);
                    storage.save(tasks);

                    System.out.println("Got it. Removing task from list:");
                    System.out.println("  " + deletedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (command.startsWith("todo ") || command.equals("todo")) {
                    String description = command.substring(4).trim();

                    tasks.add(new Todo(description));
                    storage.save(tasks);

                    System.out.println("Got it. Adding todo:");
                    System.out.println("  " + tasks.getLast());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (command.startsWith("deadline ") || command.equals("deadline")) {
                    String input = command.substring(8).trim();

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

                    tasks.add(new Deadline(description, by));
                    storage.save(tasks);

                    System.out.println("Got it. Adding deadline:");
                    System.out.println("  " + tasks.getLast());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (command.startsWith("event ") || command.equals("event")) {
                    String input = command.substring(5).trim();

                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");

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

                    tasks.add(new Event(description, from, to));
                    storage.save(tasks);

                    System.out.println("Got it. Adding event:");
                    System.out.println("  " + tasks.getLast());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else {
                    throw new NanoException("What do you mean?");
                }
            } catch (NanoException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        }
        scanner.close();
    }
}