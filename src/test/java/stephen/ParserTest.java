package stephen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Parser normalization.
 */
public class ParserTest {
    /**
     * Test for parsing commands with extra whitespace.
     */
    @Test
    public void testWhitespaceNormalization() {
        Parser parser = new Parser();
        assertEquals(Command.LIST, parser.parse("   list   "));
        assertEquals(Command.MARK, parser.parse("  mark    2  "));
        assertEquals(Command.TODO, parser.parse(" todo    read book "));
        assertEquals(Command.DEADLINE, parser.parse("  deadline   submit   /by   1/2/2026   2359  "));
        assertEquals(Command.EVENT, parser.parse("  event   meeting   /from  1/2/2026 1200  /to  1/2/2026 1300  "));
        assertEquals(Command.FIND, parser.parse("   find    movie night  "));
    }

    /**
     * Test for parsing empty input.
     */
    @Test
    public void testEmptyInputGoesUnknown() {
        Parser parser = new Parser();
        assertEquals(Command.UNKNOWN, parser.parse("     "));
    }

    /**
     * Test for parsing commands with mixed case.
     */
    @Test
    public void testExactCommands() {
        Parser parser = new Parser();
        assertEquals(Command.LIST, parser.parse("list"));
        assertEquals(Command.HELP, parser.parse("help"));
        assertEquals(Command.BYE, parser.parse("bye"));
    }

    /**
     * Test for parsing parameterized commands.
     */
    @Test
    public void testUnknownCommands() {
        Parser parser = new Parser();
        assertEquals(Command.UNKNOWN, parser.parse("unknown"));
        assertEquals(Command.UNKNOWN, parser.parse("LiSt"));
    }

    /**
     * Test for commands with correct command but wrong parameters.
     */
    @Test
    public void testCorrectCommandWrongParameters() {
        Parser parser = new Parser();
        assertEquals(Command.UNKNOWN, parser.parse("mark"));
        assertEquals(Command.UNKNOWN, parser.parse("unmark"));
        assertEquals(Command.UNKNOWN, parser.parse("delete"));
        assertEquals(Command.UNKNOWN, parser.parse("todo"));
        assertEquals(Command.UNKNOWN, parser.parse("deadline"));
        assertEquals(Command.UNKNOWN, parser.parse("event"));
        assertEquals(Command.UNKNOWN, parser.parse("find"));
    }
}
