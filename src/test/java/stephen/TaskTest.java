package stephen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import stephen.exception.TaskAlreadyInStateException;

/**
 * Unit tests for the Task class.
 */
public class TaskTest {
    /**
     * Test for task initialization.
     */
    @Test
    public void testTaskInitialization() {
        Task task = new ToDosTask("Watch a movie");
        assertFalse(task.isDone());
        assertEquals("[T] [ ] Watch a movie", task.toString());
    }

    /**
     * Test for marking a task as done.
     */
    @Test
    public void testMark() {
        Task task = new ToDosTask("Watch a movie");
        task.mark();
        assertTrue(task.isDone());
        assertEquals("[T] [X] Watch a movie", task.toString());
    }

    /**
     * Test for unmarking a completed task.
     */
    @Test
    public void testUnmark() {
        Task task = new ToDosTask("Watch a movie");
        task.mark();
        assertTrue(task.isDone());
        task.unmark();
        assertFalse(task.isDone());
        assertEquals("[T] [ ] Watch a movie", task.toString());
    }

    /**
     * Test for marking, unmarking and then marking again a task.
     */
    @Test
    public void testMarkAndUnmarkCycle() {
        Task task = new ToDosTask("Watch a movie");
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
        Task task = new ToDosTask("Watch a movie");
        assertEquals("[T] [ ] Watch a movie", task.toString());
        task.mark();
        assertEquals("[T] [X] Watch a movie", task.toString());
    }

    /**
     * Test for state exceptions when marking/unmarking.
     */
    @Test
    public void testMarkUnmarkStateExceptions() {
        Task task = new ToDosTask("Play games");
        assertThrows(TaskAlreadyInStateException.class, task::unmark);
        task.mark();
        assertThrows(TaskAlreadyInStateException.class, task::mark);
    }

    /**
     * Test for todo string format.
     */
    @Test
    public void testTodoToString() {
        Task todo = new ToDosTask("Watch a movie");
        assertEquals("[T] [ ] Watch a movie", todo.toString());
        todo.mark();
        assertEquals("[T] [X] Watch a movie", todo.toString());
    }

    /**
     * Test for deadline string format.
     */
    @Test
    public void testDeadlineToString() {
        Task deadline = new DeadlinesTask("Submit", LocalDateTime.of(2026, 2, 20, 9, 30));
        assertEquals("[D] [ ] Submit (by: Feb 20 2026 09:30)", deadline.toString());
    }

    /**
     * Test for event string format.
     */
    @Test
    public void testEventToString() {
        Task event = new EventsTask("Meet", LocalDateTime.of(2026, 2, 21, 10, 0),
                LocalDateTime.of(2026, 2, 21, 11, 0));
        assertEquals("[E] [ ] Meet (from: Feb 21 2026 10:00 to: Feb 21 2026 11:00)", event.toString());
    }
}
