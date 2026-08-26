package nano;

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

        ui.showWelcome();

        Storage storage = new Storage(DATA_FILE);
        TaskList tasks;

        try {
            tasks = storage.load();
        } catch (NanoException e) {
            ui.showMessage("Oops! " + e.getMessage());
            tasks = new TaskList();
        }

        while (true) {
            String command = ui.readCommand();

            try {
                if (command.equals("bye")) {
                    ui.showGoodbye();
                    break;

                } else if (command.equals("list")) {
                    ui.showTasks(tasks);

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

                    ui.showTaskMarked(t);

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

                    ui.showTaskUnmarked(t);

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

                    ui.showTaskDeleted(deletedTask, tasks.size());

                } else if (command.startsWith("todo ") || command.equals("todo")) {
                    String description = command.substring(4).trim();

                    tasks.add(new Todo(description));
                    storage.save(tasks);

                    ui.showTaskAdded(tasks.getLast(), tasks.size());

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

                    ui.showTaskAdded(tasks.getLast(), tasks.size());

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

                    ui.showTaskAdded(tasks.getLast(), tasks.size());

                } else {
                    throw new NanoException("What do you mean?");
                }
            } catch (NanoException e) {
                ui.showMessage("Oops! " + e.getMessage());
            }
        }
        ui.close();
    }
}