package stephen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an events task.
 */
public class EventsTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for EventsTask.
     */
    public EventsTask(String description, String from, String to) {
        super(description);
        assert from != null : "Event start time should not be null";
        assert to != null : "Event end time should not be null";
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        assert !this.from.isAfter(this.to) : "Event end time should be after start time";
    }

    /**
     * Overloaded constructor for EventsTask.
     */
    public EventsTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert from != null : "Event start time should not be null";
        assert to != null : "Event end time should not be null";
        assert !from.isAfter(to) : "Event end time should be after start time";
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns the storage representation for the events task.
     */
    @Override
    public String toStorageString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription()
                + " | " + from.toString() + " | " + to.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter outputformatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E] " + super.toString()
                + " (from: " + from.format(outputformatter) + " to: " + to.format(outputformatter) + ")";
    }
}
