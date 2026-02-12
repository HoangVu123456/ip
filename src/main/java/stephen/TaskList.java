package stephen;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Delete index should be a valid integer in range";
        return tasks.remove(index);
    }

    /**
     * Gets a specific task from the list by the index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Get index should be a valid integer in range";
        return tasks.get(index);
    }

    /**
     * Gets the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks whether the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets the list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
