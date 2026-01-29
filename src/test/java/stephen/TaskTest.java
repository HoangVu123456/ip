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
        assertFalse(task.getIsDone());
        assertEquals("[ ] Watch a movie", task.toString());
    }

    /**
     * Test for marking a task as done.
     */
    @Test
    public void testMark() {
        Task task = new Task("Watch a movie");
        task.mark();
        assertTrue(task.getIsDone());
        assertEquals("[X] Watch a movie", task.toString());
    }

    /**
     * Test for unmarking a completed task.
     */
    @Test
    public void testUnmark() {
        Task task = new Task("Watch a movie");
        task.mark();
        assertTrue(task.getIsDone());
        
        task.unmark();
        assertFalse(task.getIsDone());
        assertEquals("[ ] Watch a movie", task.toString());
    }

    /**
     * Test for marking, unmarking and then marking again a task.
     */
    @Test
    public void testMarkAndUnmarkCycle() {
        Task task = new Task("Watch a movie");
        assertFalse(task.getIsDone());
        
        task.mark();
        assertTrue(task.getIsDone());
        
        task.unmark();
        assertFalse(task.getIsDone());
        
        task.mark();
        assertTrue(task.getIsDone());
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
