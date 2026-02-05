package stephen;

/**
 * Represents a to-do task.
 */
public class ToDosTask extends Task {
    public ToDosTask(String description) {
        super(description);
    }

    /**
     * Returns the storage representation for the to-do task.
     */
    @Override
    public String toStorageString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
