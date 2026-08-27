package nano.command;

/**
 * Represents the types of commands that Nano can understand.
 */
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    UNKNOWN
}