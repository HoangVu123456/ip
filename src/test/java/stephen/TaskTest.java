package stephen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Task class.
 */
public class TaskTest {
    /**
     * Test for task initialization.
     */
    @Test
    public void testTaskInitialization() {
        Task task = new Task("Watch a movie");
        assertFalse(task.isDone());
        assertEquals("[ ] Watch a movie", task.toString());
    }

    /**
     * Test for marking a task as done.
     */
    @Test
    public void testMark() {
        Task task = new Task("Watch a movie");
        task.mark();
        assertTrue(task.isDone());
        assertEquals("[X] Watch a movie", task.toString());
    }

    /**
     * Test for unmarking a completed task.
     */
    @Test
    public void testUnmark() {
        Task task = new Task("Watch a movie");
        task.mark();
        assertTrue(task.isDone());
        task.unmark();
        assertFalse(task.isDone());
        assertEquals("[ ] Watch a movie", task.toString());
    }

    /**
     * Test for marking, unmarking and then marking again a task.
     */
    @Test
    public void testMarkAndUnmarkCycle() {
        Task task = new Task("Watch a movie");
        assertFalse(task.isDone());
        task.mark();
        assertTrue(task.isDone());
        task.unmark();
        assertFalse(task.isDone());
        task.mark();
        assertTrue(task.isDone());
    }

    /**
     * Test for the string representation of the task.
     */
    @Test
    public void testToStringFormat() {
        Task task = new Task("Watch a movie");
        assertEquals("[ ] Watch a movie", task.toString());
        task.mark();
        assertEquals("[X] Watch a movie", task.toString());
    }
}
