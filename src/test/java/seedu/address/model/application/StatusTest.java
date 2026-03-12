package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void fromUserInput_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Status.fromUserInput(null));
    }

    @Test
    public void fromUserInput_validInputs() {
        assertEquals(Status.APPLIED, Status.fromUserInput("Applied"));
        assertEquals(Status.INTERVIEW, Status.fromUserInput("Interview"));
        assertEquals(Status.OFFERED, Status.fromUserInput("Offered"));
        assertEquals(Status.REJECTED, Status.fromUserInput("Rejected"));
        assertEquals(Status.WITHDRAWN, Status.fromUserInput("Withdrawn"));
    }

    @Test
    public void fromUserInput_caseInsensitive() {
        assertEquals(Status.APPLIED, Status.fromUserInput("applied"));
        assertEquals(Status.APPLIED, Status.fromUserInput("APPLIED"));
        assertEquals(Status.APPLIED, Status.fromUserInput("ApPlIeD"));
    }

    @Test
    public void fromUserInput_whitespaceHandling() {
        assertEquals(Status.APPLIED, Status.fromUserInput("  Applied  "));
        assertEquals(Status.INTERVIEW, Status.fromUserInput("\tInterview\n"));
    }

    @Test
    public void fromUserInput_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Status.fromUserInput("Invalid"));
        assertThrows(IllegalArgumentException.class, () -> Status.fromUserInput(""));
        assertThrows(IllegalArgumentException.class, () -> Status.fromUserInput("Pending"));
    }

    @Test
    public void toString_correctFormat() {
        assertEquals("Applied", Status.APPLIED.toString());
        assertEquals("Interview", Status.INTERVIEW.toString());
        assertEquals("Offered", Status.OFFERED.toString());
        assertEquals("Rejected", Status.REJECTED.toString());
        assertEquals("Withdrawn", Status.WITHDRAWN.toString());
    }
}
