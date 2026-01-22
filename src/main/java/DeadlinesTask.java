public class DeadlinesTask extends Task {
    private String deadlines;

    public DeadlinesTask(String description, String deadlines) {
        super(description);
        this.deadlines = deadlines;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadlines + ")";
    }
}
