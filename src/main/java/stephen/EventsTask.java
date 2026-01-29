package stephen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an events task.
 */
public class EventsTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public EventsTask(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    public EventsTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputformatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E] " + super.toString() + 
                " (from: " + from.format(outputformatter) + " to: " + to.format(outputformatter) + ")";
    }
}
