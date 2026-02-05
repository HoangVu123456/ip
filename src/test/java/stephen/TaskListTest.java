package stephen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {
    /**
     * Test for initialization of an empty TaskList.
     */
    @Test
    public void testEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
        assertEquals(0, taskList.size());
    }

    /**
     * Test for adding a single task to the TaskList.
     */
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDosTask("Play football");
        taskList.addTask(task1);
        assertFalse(taskList.isEmpty());
        assertEquals(1, taskList.size());
        assertEquals(task1, taskList.getTask(0));
    }

    /**
     * Test for adding multiple tasks to the TaskList consecutively.
     */
    @Test
    public void testAddMultipleTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDosTask("Play football");
        Task task2 = new ToDosTask("Play video games");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
    }

    /**
     * Test for deleting a task from the TaskList.
     */
    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDosTask("Play football");
        Task task2 = new ToDosTask("Play video games");
        taskList.addTask(task1);
        taskList.addTask(task2);
        Task deleted = taskList.deleteTask(0);
        assertEquals(task1, deleted);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }

    /**
     * Test for deleting the last task in the TaskList.
     */
    @Test
    public void testDeleteLastTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDosTask("Play football");
        taskList.addTask(task1);
        taskList.deleteTask(0);
        assertTrue(taskList.isEmpty());
        assertEquals(0, taskList.size());
    }

    /**
     * Test for retrieving tasks by the index.
     */
    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDosTask("Play football");
        Task task2 = new ToDosTask("Play video games");
        taskList.addTask(task1);
        taskList.addTask(task2);
        Task retrieved1 = taskList.getTask(0);
        Task retrieved2 = taskList.getTask(1);
        assertEquals(task1, retrieved1);
        assertEquals(task2, retrieved2);
    }

    /**
     * Test for getting the size of the TaskList.
     */
    @Test
    public void testSize() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDosTask("Play football");
        Task task2 = new ToDosTask("Play video games");
        assertEquals(0, taskList.size());
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
    }

    /**
     * Test for retrieving multiple tasks in the TaskList.
     */
    @Test
    public void testGetTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDosTask("Play football");
        Task task2 = new ToDosTask("Play video games");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.getTasks().size());
        assertEquals(task1, taskList.getTasks().get(0));
        assertEquals(task2, taskList.getTasks().get(1));
    }
}
