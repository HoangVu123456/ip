package stephen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadlne task.
 */
public class DeadlinesTask extends Task {
    private LocalDateTime deadlines;

    /**
     * Constructor for DeadlineTask.
     */
    public DeadlinesTask(String description, String deadlines) {
        super(description);
        assert deadlines != null : "Deadline time should not be null";
        this.deadlines = LocalDateTime.parse(deadlines, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Overloaded constructor for DeadlineTask.
     */
    public DeadlinesTask(String description, LocalDateTime deadlines) {
        super(description);
        assert deadlines != null : "Deadline time should not be null";
        this.deadlines = deadlines;
    }

    public LocalDateTime getDeadlines() {
        return this.deadlines;
    }

    /**
     * Returns the storage representation for the deadline task.
     */
    @Override
    public String toStorageString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription()
                + " | " + deadlines.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter outputformatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D] " + super.toString() + " (by: " + deadlines.format(outputformatter) + ")";
    }
}
