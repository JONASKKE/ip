public class Event extends Task{
    private String from;
    private String to;

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
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getDescription()
                + " (from: " + from + " to: " + to + ")";
    }
}
