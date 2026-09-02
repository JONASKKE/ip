package nano.command;

import nano.NanoException;
import nano.task.Deadline;
import nano.task.Event;
import nano.task.Task;
import nano.task.Todo;

/**
 * Parses user commands and identifies their command types.
 */
public class Parser {

    /**
     * Identifies the type of the given user command.
     *
     * @param command user command.
     * @return the type of the command.
     */
    public CommandType parseCommand(String command) {
        if (command.equals("bye")) {
            return CommandType.BYE;

        } else if (command.equals("list")) {
            return CommandType.LIST;

        } else if (command.startsWith("mark ")
                || command.equals("mark")) {
            return CommandType.MARK;

        } else if (command.startsWith("unmark ")
                || command.equals("unmark")) {
            return CommandType.UNMARK;

        } else if (command.startsWith("delete ")
                || command.equals("delete")) {
            return CommandType.DELETE;

        } else if (command.startsWith("todo ")
                || command.equals("todo")) {
            return CommandType.TODO;

        } else if (command.startsWith("deadline ")
                || command.equals("deadline")) {
            return CommandType.DEADLINE;

        } else if (command.startsWith("event ")
                || command.equals("event")) {
            return CommandType.EVENT;
        } else if (command.startsWith("find ")
                || command.equals("find")) {
            return CommandType.FIND;
        }

        return CommandType.UNKNOWN;
    }

    /**
     * Parses a command into the appropriate task.
     *
     * @param command user command describing a task.
     * @return the task represented by the command.
     * @throws NanoException if the command is invalid.
     */
    public Task parseTask(String command) throws NanoException {
        if (command.startsWith("todo ")) {
            String description = command.substring(5).trim();
            return new Todo(description);

        } else if (command.startsWith("deadline ")) {
            String input = command.substring(9).trim();

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

            return new Deadline(description, by);

        } else if (command.startsWith("event ")) {
            String input = command.substring(6).trim();

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

            return new Event(description, from, to);
        }

        throw new NanoException("Unknown task command.");
    }

    /**
     * Extracts the task number from a command.
     *
     * @param command user command containing a task number.
     * @return the task number.
     * @throws NanoException if the task number is not a valid number.
     */
    public int parseTaskNumber(String command) throws NanoException {
        try {
            return Integer.parseInt(
                    command.substring(command.indexOf(" ") + 1).trim()
            );
        } catch (NumberFormatException e) {
            throw new NanoException("The task number must be a number.");
        }
    }

    /**
     * Extracts the keyword from a find command.
     *
     * @param command user command containing a keyword.
     * @return the keyword to find.
     * @throws NanoException if no keyword provided.
     */
    public String parseFindKeyword(String command) throws NanoException {
        String keyword = command.substring(4).trim();

        if (keyword.isEmpty()) {
            throw new NanoException("Please provide a keyword to search for.");
        }

        return keyword;
    }

    /**
     * Parses the user's input into a command.
     *
     * @param input user input
     * @return the command represented by the input
     * @throws NanoException if the input is invalid
     */
    public Command parse(String input) throws NanoException {
        CommandType commandType = parseCommand(input);

        switch (commandType) {
            case BYE:
                return new ByeCommand();

            case LIST:
                return new ListCommand();

            case MARK:
                return new MarkCommand(parseTaskNumber(input));

            case UNMARK:
                return new UnmarkCommand(parseTaskNumber(input));

            case DELETE:
                return new DeleteCommand(parseTaskNumber(input));

            case FIND:
                return new FindCommand(parseFindKeyword(input));

            case TODO:
            case DEADLINE:
            case EVENT:
                return new AddCommand(parseTask(input));

            default:
                throw new NanoException("What do you mean?");
        }
    }
}
