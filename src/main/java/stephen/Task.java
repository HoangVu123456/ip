package stephen;

/**
 * Class representing a generic task.
 */
public class Task {
    private String description;
    private boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task has been done.
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

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
