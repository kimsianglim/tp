package seedu.address.model.util;

import java.util.Optional;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.application.Application;
import seedu.address.model.application.ApplicationDate;
import seedu.address.model.application.Company;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;
import seedu.address.model.application.Url;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] {
            new Application(new Company("Google"), new Role("AI Research Intern"),
                    new ApplicationDate("2025-02-14"),
                    Optional.of(new Url("https://www.alexyeoh.com")),
                    Status.APPLIED),
            new Application(new Company("Tencent"), new Role("Software Engineer Intern"),
                    new ApplicationDate("2025-12-25"),
                    Optional.of(new Url("https://www.berniceyu.com")),
                    Status.INTERVIEW),
            new Application(new Company("Meta"), new Role("AI Research Intern"),
                    new ApplicationDate("2025-01-01"),
                    Optional.of(new Url("https://www.google.com")),
                    Status.OFFERED),
            new Application(new Company("Optiver"), new Role("AI Research Intern"),
                    new ApplicationDate("2025-04-01"),
                    Optional.of(new Url("https://www.lidavid.com")),
                    Status.REJECTED),
            new Application(new Company("NUS"), new Role("AI Research Intern"),
                    new ApplicationDate("2025-10-31"),
                    Optional.of(new Url("https://www.irfan.com")),
                    Status.WITHDRAWN),
            new Application(new Company("Apple"), new Role("AI Research Intern"),
                    new ApplicationDate("2025-05-20"),
                    Optional.of(new Url("https://www.roybalakrishnan.com")),
                    Status.APPLIED)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Application sampleApplication : getSampleApplications()) {
            sampleAb.addApplication(sampleApplication);
        }
        return sampleAb;
    }

}
