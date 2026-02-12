package stephen;

/**
 * Represents a generic task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the storage representation for this task.
     */
    public abstract String toStorageString();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
