package nano;

import nano.command.CommandType;
import nano.command.Parser;
import nano.task.Task;
import nano.task.TaskList;

/**
 * Runs the Nano chatbot and handles user commands.
 */
public class Nano {
    private static final String DATA_FILE = "./data/nano.txt";

    /**
     * Starts the Nano chatbot and processes user commands.
     *
     * @param args command-line arguments.
     * @throws NanoException if an error occurs while loading or saving tasks.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();

        ui.showWelcome();

        Storage storage = new Storage(DATA_FILE);
        TaskList tasks;

        try {
            tasks = storage.load();
        } catch (NanoException e) {
            ui.showMessage("Oops! " + e.getMessage());
            tasks = new TaskList();
        }

        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readCommand();
            CommandType commandType = parser.parseCommand(command);

            try {
                switch (commandType) {
                    case BYE: {
                        ui.showGoodbye();
                        isRunning = false;
                        break;
                    }

                    case LIST: {
                        ui.showTasks(tasks);
                        break;
                    }

                    case MARK: {
                        int taskNumber = parser.parseTaskNumber(command);
                        Task task = tasks.getTask(taskNumber);

                        task.markDone();
                        storage.save(tasks);

                        ui.showTaskMarked(task);
                        break;
                    }

                    case UNMARK: {
                        int taskNumber = parser.parseTaskNumber(command);
                        Task task = tasks.getTask(taskNumber);

                        task.markUndone();
                        storage.save(tasks);

                        ui.showTaskUnmarked(task);
                        break;
                    }

                    case DELETE: {
                        int taskNumber = parser.parseTaskNumber(command);
                        Task deletedTask = tasks.deleteTask(taskNumber);

                        storage.save(tasks);

                        ui.showTaskDeleted(deletedTask, tasks.size());
                        break;
                    }

                    case TODO:
                    case DEADLINE:
                    case EVENT: {
                        Task task = parser.parseTask(command);

                        tasks.add(task);
                        storage.save(tasks);

                        ui.showTaskAdded(tasks.getLast(), tasks.size());
                        break;
                    }

                    case FIND: {
                        String keyword = parser.parseFindKeyword(command);
                        TaskList matchingTasks = tasks.find(keyword);

                        ui.showMatchingTasks(matchingTasks);
                        break;
                    }

                    default:
                        throw new NanoException("What do you mean?");
                }
            } catch (NanoException e) {
                ui.showMessage("Oops! " + e.getMessage());
            }
        }
        ui.close();
    }
}
