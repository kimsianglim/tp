package seedu.address.model.application;

import java.util.Locale;

/**
 * Represents the status of an application in the address book.
 * Guarantees: immutable; can only take predefined values declared in {@link Status}.
 */
public enum Status {
    APPLIED,
    INTERVIEW,
    OFFERED,
    REJECTED,
    WITHDRAWN;

    public static final Status DEFAULT = APPLIED;

    public static final String MESSAGE_CONSTRAINTS =
            "Status must be one of: Applied, Interview, Offered, Rejected, Withdrawn.";

    /**
     * Returns the {@code Status} corresponding to the given user input.
     * Input is case-insensitive and leading/trailing whitespace will be ignored.
     *
     * @param input User-provided status string.
     * @return Corresponding {@code Status}.
     * @throws IllegalArgumentException if the input does not match any valid status.
     */
    public static Status fromUserInput(String input) {
        String normalized = input.trim().toUpperCase(Locale.ROOT);
        return Status.valueOf(normalized);
    }

    @Override
    public String toString() {
        String lower = name().toLowerCase(Locale.ROOT);
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
