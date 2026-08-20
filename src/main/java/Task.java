public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) throws NanoException {
        this.description = description.trim();
        this.isDone = false;

        if (this.description.isEmpty()) {
            throw new NanoException("The description cannot be empty.");
        }
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}