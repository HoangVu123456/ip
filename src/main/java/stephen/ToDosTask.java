package stephen;

/**
 * Class representing a to-do task.
 */
public class ToDosTask extends Task {
    public ToDosTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
