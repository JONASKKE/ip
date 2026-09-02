package nano.command;

import nano.NanoException;
import nano.Storage;
import nano.task.TaskList;

/**
 * Represents the bye command.
 */
public class ByeCommand implements Command {

    @Override
    public String execute(TaskList tasks, Storage storage) throws NanoException {
        return "Bye! Hope to see you again soon.";
    }
}
