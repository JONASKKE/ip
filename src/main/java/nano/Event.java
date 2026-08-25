package nano;

/**
 * Represents a task that takes place between a specified start and end time.
 */
public class Event extends Task{
    private String from;
    private String to;

    /**
     * Creates an event task with the specified description, start time, and end time.
     *
     * @param description description of the event.
     * @param from start date or time of the event.
     * @param to end date or time of the event.
     * @throws NanoException if the description, start time, or end time is empty.
     */
    public Event(String description, String from, String to) throws NanoException {
        super(description);

        if (from.trim().isEmpty()) {
            throw new NanoException("The /from time of an event cannot be empty.");
        }

        if (to.trim().isEmpty()) {
            throw new NanoException("The /to time of an event cannot be empty.");
        }

        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone() ? "1" : "0")
                + " | " + getDescription()
                + " | " + from
                + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getDescription()
                + " (from: " + from + " to: " + to + ")";
    }
}
