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

        while (true) {
            String command = scanner.nextLine();

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
            }

            System.out.println(command);
        }

        scanner.close();
    }
}