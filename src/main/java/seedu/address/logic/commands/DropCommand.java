package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Status;

/**
 * Deletes all applications with terminal statuses (Rejected or Withdrawn).
 */
public class DropCommand extends Command {

    public static final String COMMAND_WORD = "drop";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all applications with status Rejected or Withdrawn.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DROP_APPLICATIONS_SUCCESS =
            "Dropped %1$d \"REJECTED\"/\"WITHDRAWN\" application(s).";
    public static final String MESSAGE_DROPPED_APPLICATIONS_HEADER =
            "Dropped \"REJECTED\" and \"WITHDRAWN\" applications:";
    public static final String MESSAGE_NO_REJECTED_WITHDRAWN_IN_CURRENT_LIST =
            "No \"REJECTED\" or \"WITHDRAWN\" applications in the current list.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Application> applicationsToDelete = model.getFilteredApplicationList().stream()
                .filter(application -> application.getStatus() == Status.REJECTED
                        || application.getStatus() == Status.WITHDRAWN)
                .collect(Collectors.toList());

        if (applicationsToDelete.isEmpty()) {
            throw new CommandException(MESSAGE_NO_REJECTED_WITHDRAWN_IN_CURRENT_LIST);
        }

        applicationsToDelete.forEach(model::deleteApplication);

        String message = String.format(MESSAGE_DROP_APPLICATIONS_SUCCESS, applicationsToDelete.size());
        String droppedApplications = applicationsToDelete.stream()
                .map(application -> "- " + Messages.format(application))
                .collect(Collectors.joining("\n"));
        message = message + "\n" + MESSAGE_DROPPED_APPLICATIONS_HEADER + "\n" + droppedApplications;

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DropCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
