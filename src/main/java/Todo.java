public class Todo extends Task {
    public Todo(String description) throws NanoException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + getDescription();
    }
}
