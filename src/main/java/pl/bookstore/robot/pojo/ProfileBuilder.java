package pl.bookstore.robot.pojo;

/**
 * Created by damian on 22.04.16.
 */
public class ProfileBuilder {
    private static Profile profile;

    public static Profile build(BookStore bookStore, String... categories) {
        profile = new Profile("Category");
        profile.setBookStore(bookStore);
        profile.addCategories(categories);
        return profile;
    };

    private ProfileBuilder() {
        profile = new Profile("Category");
    }

}
