package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearNoteCommand;

public class ClearNoteCommandParserTest {

    private final ClearNoteCommandParser parser = new ClearNoteCommandParser();

    @Test
    public void parse_validArgs_returnsClearNoteCommand() {
        assertParseSuccess(parser, "1", new ClearNoteCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraArguments_throwsParseException() {
        assertParseFailure(parser, "1 extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearNoteCommand.MESSAGE_USAGE));
    }
}
