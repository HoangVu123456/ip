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
 * Class handling the load and save chat history data to the local storage file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/chat_history_data.txt";

    private Storage() {}

    private static Task parseLineToTask(String data) {
        String[] parts = data.split(" \\| ");
        String taskType = parts[0];
        String isDoneString = parts[1];
        String description = parts[2];

        Task task = null;

        Boolean isDone = null;
        if (isDoneString.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }

        switch (taskType) {
            case "T":
                task = new ToDosTask(description);
                break;
            case "D":
                String by = parts[3];
                LocalDateTime byTime = LocalDateTime.parse(by);
                task = new DeadlinesTask(description, byTime);
                break;
            case "E":
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
    public static List<Task> load() {
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
     * Saves and write the chat history to the local storage file.
     */
    public static void save(List<Task> history) {
        try {
            File directory = new File("./data");
            
            if (directory.exists() == false) {
                directory.mkdir();
            }

            FileWriter fw = new FileWriter(FILE_PATH);

            for (int i = 0; i < history.size(); i++) {
                Task task = history.get(i);
                boolean isDone = task.getIsDone();
                String line = "";

                if (task instanceof ToDosTask) {
                    line += "T | ";
                } else if (task instanceof DeadlinesTask) {
                    line += "D | ";
                } else if (task instanceof EventsTask) {
                    line += "E | ";
                }

                if (isDone) {
                    line += "1 | ";
                } else {
                    line += "0 | ";
                }

                if (task instanceof ToDosTask) {
                    line += task.toString().substring(8);
                } else if (task instanceof DeadlinesTask) {
                    DeadlinesTask deadlineTask = (DeadlinesTask) task;
                    line += deadlineTask.toString().substring(8).split(" \\(by: ")[0] + " | "
                        + deadlineTask.getDeadlines().toString();
                } else if (task instanceof EventsTask) {
                    EventsTask eventTask = (EventsTask) task;
                    line += eventTask.toString().substring(8).split(" \\(from: ")[0] + " | "
                        + eventTask.getFrom().toString() + " | " + eventTask.getTo().toString();
                }

                fw.write(line + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}