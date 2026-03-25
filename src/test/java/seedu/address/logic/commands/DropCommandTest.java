package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplications.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DropCommand}.
 */
public class DropCommandTest {

    @Test
    public void execute_containsRejectedAndWithdrawn_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Application rejected = new ApplicationBuilder().withCompany("Reject Co").withRole("Backend Intern")
                .withApplicationDate("2026-04-01").withStatus("Rejected").build();
        Application withdrawn = new ApplicationBuilder().withCompany("Withdraw Co").withRole("Frontend Intern")
                .withApplicationDate("2026-04-02").withStatus("Withdrawn").build();
        model.addApplication(rejected);
        model.addApplication(withdrawn);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteApplication(rejected);
        expectedModel.deleteApplication(withdrawn);

        String expectedMessage = getExpectedDropMessage(List.of(rejected, withdrawn));
        assertCommandSuccess(new DropCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noRejectedOrWithdrawnInCurrentList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandFailure(new DropCommand(), model,
                DropCommand.MESSAGE_NO_REJECTED_WITHDRAWN_IN_CURRENT_LIST);
    }

    @Test
    public void execute_filteredListWithoutRejectedWithdrawn_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Application rejected = new ApplicationBuilder().withCompany("Reject Co").withRole("DevOps Intern")
                .withApplicationDate("2026-04-03").withStatus("Rejected").build();
        model.addApplication(rejected);

        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        assertCommandFailure(new DropCommand(), model,
                DropCommand.MESSAGE_NO_REJECTED_WITHDRAWN_IN_CURRENT_LIST);
    }

    @Test
    public void equals() {
        DropCommand dropCommand = new DropCommand();

        assertTrue(dropCommand.equals(dropCommand));
        assertTrue(dropCommand.equals(new DropCommand()));
        assertFalse(dropCommand.equals(1));
        assertFalse(dropCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        DropCommand dropCommand = new DropCommand();
        String expected = DropCommand.class.getCanonicalName() + "{}";
        assertEquals(expected, dropCommand.toString());
    }

    private static String getExpectedDropMessage(List<Application> droppedApplications) {
        String message = String.format(DropCommand.MESSAGE_DROP_APPLICATIONS_SUCCESS, droppedApplications.size());

        String details = droppedApplications.stream()
                .map(application -> "- " + Messages.format(application))
                .collect(Collectors.joining("\n"));
        return message + "\n" + DropCommand.MESSAGE_DROPPED_APPLICATIONS_HEADER + "\n" + details;
    }
}
