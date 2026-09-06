package nano.ui;

import java.util.Scanner;

/**
 * Handles interactions between Nano and the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates an Ui object for reading user input.
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
