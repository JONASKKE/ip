package nano.task;

import nano.DateTimeUtil;
import nano.NanoException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that takes place between a specified start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

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

        try {
            this.from = LocalDateTime.parse(
                    from.trim(),
                    DateTimeUtil.STORAGE_FORMATTER
            );

            this.to = LocalDateTime.parse(
                    to.trim(),
                    DateTimeUtil.STORAGE_FORMATTER
            );
        } catch (DateTimeParseException e) {
            throw new NanoException(
                    "Event dates must use yyyy-MM-dd HHmm format."
            );
        }
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone() ? "1" : "0")
                + " | " + getDescription()
                + " | " + DateTimeUtil.formatForStorage(from)
                + " | " + DateTimeUtil.formatForStorage(to);
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getDescription()
                + " (from: " + DateTimeUtil.formatForDisplay(from)
                + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }
}
