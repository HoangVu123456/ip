package stephen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage handler for loading and saving chat history.
 */
public class Storage {
    private static final String FILE_PATH = "./data/chat_history_data.txt";

    public Storage() {}

    /**
     * Parses a line from the storage file into a Task object.
     */
    private Task parseLineToTask(String data) {
        String[] parts = data.split(" \\| ");
        assert parts.length >= 3 : "Storage line should have at least 3 parts";
        String taskType = parts[0];
        String isDoneString = parts[1];
        String description = parts[2];

        Task task = null;

        assert isDoneString.equals("1") || isDoneString.equals("0")
                : "Completion flag should be 1 or 0";
        Boolean isDone = isDoneString.equals("1");

        switch (taskType) {
        case "T":
            task = new ToDosTask(description);
            break;
        case "D":
            assert parts.length >= 4 : "Deadline task should have by time part";
            String by = parts[3];
            LocalDateTime byTime = LocalDateTime.parse(by);
            task = new DeadlinesTask(description, byTime);
            break;
        case "E":
            assert parts.length >= 5 : "Event task should have from/to time parts";
            String from = parts[3];
            String to = parts[4];
            LocalDateTime fromTime = LocalDateTime.parse(from);
            LocalDateTime toTime = LocalDateTime.parse(to);
            task = new EventsTask(description, fromTime, toTime);
            break;
        default:
            return null;
        }

        if (task != null && isDone == true) {
            task.mark();
        } else {
            task.unmark();
        }

        return task;
    }

    /**
     * Loads the chat history from the local storage file.
     */
    public List<Task> load() {
        List<Task> history = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return history;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String data;

            while ((data = br.readLine()) != null) {
                Task task = parseLineToTask(data);
                if (task != null) {
                    history.add(task);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return history;
    }

    /**
     * Saves and writes the chat history to the local storage file.
     */
    public void save(List<Task> history) {
        try {
            File directory = new File("./data");
            if (directory.exists() == false) {
                directory.mkdir();
            }

            FileWriter fw = new FileWriter(FILE_PATH);

            for (int i = 0; i < history.size(); i++) {
                Task task = history.get(i);
                fw.write(task.toStorageString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
