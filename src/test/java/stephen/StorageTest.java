package stephen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Storage.
 */
public class StorageTest {
    private static final Path DATA_PATH = Paths.get("./data/chat_history_data.txt");
    private byte[] originalContent;
    private boolean hadFile;

    /**
     * Backs up the storage file if exists before each test and
     * deletes it to preserve test isolation.
     */
    @BeforeEach
    public void backupStorageFile() throws IOException {
        if (Files.exists(DATA_PATH)) {
            originalContent = Files.readAllBytes(DATA_PATH);
            hadFile = true;
        } else {
            hadFile = false;
        }
        Files.deleteIfExists(DATA_PATH);
    }

    /**
     * Restores the original storage file after each test.
     */
    @AfterEach
    public void restoreStorageFile() throws IOException {
        if (hadFile) {
            Files.createDirectories(DATA_PATH.getParent());
            Files.write(DATA_PATH, originalContent);
        } else {
            Files.deleteIfExists(DATA_PATH);
        }
    }

    /**
     * Test for loading from a missing file.
     */
    @Test
    public void testLoadMissingFileReturnsEmpty() {
        Storage storage = new Storage();
        assertTrue(storage.load().isEmpty());
    }

    /**
     * Test for saving and loading a list of tasks.
     */
    @Test
    public void testSaveAndLoadRoundTrip() {
        Storage storage = new Storage();
        List<Task> tasks = new ArrayList<>();

        Task todo = new ToDosTask("Read book");
        Task deadline = new DeadlinesTask("Submit", LocalDateTime.of(2026, 2, 20, 9, 30));
        Task event = new EventsTask("Meet", LocalDateTime.of(2026, 2, 21, 10, 0),
                LocalDateTime.of(2026, 2, 21, 11, 0));
        event.mark();

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        storage.save(tasks);
        List<Task> loaded = storage.load();

        assertEquals(3, loaded.size());
        assertEquals(tasks.get(0).toString(), loaded.get(0).toString());
        assertEquals(tasks.get(1).toString(), loaded.get(1).toString());
        assertEquals(tasks.get(2).toString(), loaded.get(2).toString());
        assertEquals(tasks.get(2).isDone(), loaded.get(2).isDone());
    }
}
