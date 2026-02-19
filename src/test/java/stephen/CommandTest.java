package stephen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Command parsing.
 */
public class CommandTest {
    /**
     * Test for parsing exact commands.
     */
    @Test
    public void testExactCommands() {
        assertEquals(Command.LIST, Command.command("list"));
        assertEquals(Command.HELP, Command.command("help"));
        assertEquals(Command.BYE, Command.command("bye"));
    }

    /**
     * Test for parsing parameterized commands.
     */
    @Test
    public void testParameterizedCommands() {
        assertEquals(Command.MARK, Command.command("mark 1"));
        assertEquals(Command.UNMARK, Command.command("unmark 2"));
        assertEquals(Command.DELETE, Command.command("delete 3"));
        assertEquals(Command.TODO, Command.command("todo read"));
        assertEquals(Command.DEADLINE, Command.command("deadline submit /by 1/2/2026 2359"));
        assertEquals(Command.EVENT, Command.command("event meet /from 1/2/2026 1200 /to 1/2/2026 1300"));
        assertEquals(Command.FIND, Command.command("find movie"));
    }

    /**
     * Test for parsing unknown commands.
     */
    @Test
    public void testUnknownCommand() {
        assertEquals(Command.UNKNOWN, Command.command("something else"));
    }
}
