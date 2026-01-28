package stephen;

/**
 * Class representing an event task.
 */
public class EventsTask extends Task {
    private String from;
    private String to;

    public EventsTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
