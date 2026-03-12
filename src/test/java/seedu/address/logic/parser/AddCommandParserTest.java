package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICATION_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_URL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATION_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalApplications.AMY;
import static seedu.address.testutil.TypicalApplications.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.application.Application;
import seedu.address.model.application.ApplicationDate;
import seedu.address.model.application.Company;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;
import seedu.address.model.application.Url;
import seedu.address.testutil.ApplicationBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Application expectedApplication = new ApplicationBuilder(BOB).withStatus(VALID_STATUS_BOB).build();

        //whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_DESC_BOB + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB
                + URL_DESC_BOB + STATUS_DESC_BOB, new AddCommand(expectedApplication));
    }

    @Test
    public void parse_repeatedSingleValue_failure() {
        String validExpectedApplicationString = COMPANY_DESC_BOB + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB
                + URL_DESC_BOB + STATUS_DESC_BOB;

        // multiple company names
        assertParseFailure(parser, COMPANY_DESC_AMY + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // multiple roles
        assertParseFailure(parser, ROLE_DESC_AMY + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple application dates
        assertParseFailure(parser, APPLICATION_DATE_DESC_AMY + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPLICATION_DATE));

        // multiple urls
        assertParseFailure(parser, URL_DESC_AMY + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_URL));

        // multiple statuses
        assertParseFailure(parser, STATUS_DESC_AMY + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedApplicationString + ROLE_DESC_AMY + APPLICATION_DATE_DESC_AMY + COMPANY_DESC_AMY
                        + URL_DESC_AMY + STATUS_DESC_AMY + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY, PREFIX_URL,
                        PREFIX_APPLICATION_DATE, PREFIX_ROLE, PREFIX_STATUS));

        // invalid value followed by valid value

        // invalid company
        assertParseFailure(parser, INVALID_COMPANY_DESC + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // invalid application date
        assertParseFailure(parser, INVALID_APPLICATION_DATE_DESC + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPLICATION_DATE));

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid url
        assertParseFailure(parser, INVALID_URL_DESC + validExpectedApplicationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_URL));

        // valid value followed by invalid value

        // invalid company
        assertParseFailure(parser, validExpectedApplicationString + INVALID_COMPANY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // invalid application date
        assertParseFailure(parser, validExpectedApplicationString + INVALID_APPLICATION_DATE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPLICATION_DATE));

        // invalid role
        assertParseFailure(parser, validExpectedApplicationString + INVALID_ROLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid url
        assertParseFailure(parser, validExpectedApplicationString + INVALID_URL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_URL));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // missing url
        Application expectedApplication = new ApplicationBuilder(AMY).withUrl(null).build();
        assertParseSuccess(parser, COMPANY_DESC_AMY + ROLE_DESC_AMY + APPLICATION_DATE_DESC_AMY + STATUS_DESC_AMY,
                new AddCommand(expectedApplication));

        // missing status
        expectedApplication = new ApplicationBuilder(AMY).withStatus(VALID_STATUS_AMY).build();
        assertParseSuccess(parser, COMPANY_DESC_AMY + ROLE_DESC_AMY + APPLICATION_DATE_DESC_AMY + URL_DESC_AMY
                + STATUS_DESC_AMY,
                new AddCommand(expectedApplication));
    }



    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser, VALID_COMPANY_BOB + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB,
                expectedMessage);

        // missing role prefix
        assertParseFailure(parser, COMPANY_DESC_BOB + VALID_ROLE_BOB + APPLICATION_DATE_DESC_BOB,
                expectedMessage);

        // missing application date prefix
        assertParseFailure(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + VALID_APPLICATION_DATE_BOB,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_BOB + VALID_ROLE_BOB + VALID_APPLICATION_DATE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser, INVALID_COMPANY_DESC + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB + URL_DESC_BOB
                + STATUS_DESC_BOB, Company.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, COMPANY_DESC_BOB + INVALID_ROLE_DESC + APPLICATION_DATE_DESC_BOB + URL_DESC_BOB
                + STATUS_DESC_BOB, Role.MESSAGE_CONSTRAINTS);

        // invalid application date
        assertParseFailure(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + INVALID_APPLICATION_DATE_DESC + URL_DESC_BOB
                + STATUS_DESC_BOB, ApplicationDate.MESSAGE_CONSTRAINTS);

        // invalid url
        assertParseFailure(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB + INVALID_URL_DESC
                + STATUS_DESC_BOB, Url.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB + URL_DESC_BOB
                + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_DESC + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB
                        + INVALID_URL_DESC,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_DESC_BOB + ROLE_DESC_BOB + APPLICATION_DATE_DESC_BOB
                + URL_DESC_BOB + STATUS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
