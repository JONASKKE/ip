public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) throws NanoException {
        super(description);

        if (by.trim().isEmpty()) {
            throw new NanoException("The /by date of a deadline cannot be empty.");
        }

        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + getDescription()
                + " (by: " + by + ")";
    }
}
