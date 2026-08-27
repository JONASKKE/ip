package nano;

import nano.task.Deadline;
import nano.task.Event;
import nano.task.Task;
import nano.task.TaskList;
import nano.task.Todo;
import java.util.Scanner;

/**
 * Handles interactions between Nano and the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates a Ui object for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return the user's command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the current tasks in the task list.
     *
     * @param tasks tasks to display.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays a message confirming that a task was added.
     *
     * @param task task that was added.
     * @param taskCount number of tasks currently in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. Adding " + getTaskType(task) + ":");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task was marked as done.
     *
     * @param task task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Keep up the good work! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message confirming that a task was marked as not done.
     *
     * @param task task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message confirming that a task was deleted.
     *
     * @param task task that was deleted.
     * @param taskCount number of tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Got it. Removing task from list:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Returns the type of the specified task.
     *
     * @param task task whose type is needed.
     * @return the type of the task.
     */
    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "todo";
        } else if (task instanceof Deadline) {
            return "deadline";
        } else if (task instanceof Event) {
            return "event";
        }

        return "task";
    }

    /**
     * Displays the Nano welcome message.
     */
    public void showWelcome() {
        String banner = "NN   NN   AAA   NN   NN   OOO \n"
                + "NNN  NN  AA AA  NNN  NN  OO OO\n"
                + "NN N NN AA   AA NN N NN OO  OO\n"
                + "NN  NNN AAAAAAA NN  NNN OO  OO\n"
                + "NN   NN AA   AA NN   NN  OOO \n";

        System.out.println(banner);
        System.out.println("Hello! I'm Nano.");
        System.out.println("How may i assist you?");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println(
                "██████╗ ██╗   ██╗███████╗\n"
                        + "██╔══██╗╚██╗ ██╔╝██╔════╝\n"
                        + "██████╔╝ ╚████╔╝ █████╗  \n"
                        + "██╔══██╗  ╚██╔╝  ██╔══╝  \n"
                        + "██████╔╝   ██║   ███████╗\n"
                        + "╚═════╝    ╚═╝   ╚══════╝"
        );
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Closes the input scanner.
     */
    public void close() {
        scanner.close();
    }
}