package pl.bookstore.robot.booksearch;

/**
 * Created by damian on 22.04.16.
 */
public class ProfileBuilder {
    Profile profile1;

    public ProfileBuilder() {
        profile1=new Profile("Kategory");
        profile1.add("Inoformatyka");
        profile1.add("Romance");
    }

    public Profile getProfile1() {
        return profile1;
    }
}
