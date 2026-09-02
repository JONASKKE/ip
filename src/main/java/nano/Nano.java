package nano;

import nano.command.Command;
import nano.command.Parser;
import nano.task.TaskList;
import nano.ui.Ui;

/**
 * Runs the Nano chatbot and handles user commands.
 */
public class Nano {
    private static final String DATA_FILE = "./data/nano.txt";

    private final Parser parser = new Parser();
    private final Storage storage = new Storage(DATA_FILE);
    private TaskList tasks;

    public Nano() {
        try {
            tasks = storage.load();
        } catch (NanoException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Nano chatbot and processes user commands.
     *
     * @param args command-line arguments.
     * @throws NanoException if an error occurs while loading or saving tasks.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Nano nano = new Nano();

        ui.showWelcome();

        boolean isRunning = true;

        while (isRunning) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                ui.showGoodbye();
                isRunning = false;
                continue;
            }

            ui.showMessage(nano.getResponse(input));
        }

        ui.close();
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(tasks, storage);
        } catch (NanoException e) {
            return "Oops! " + e.getMessage();
        }
    }
}
