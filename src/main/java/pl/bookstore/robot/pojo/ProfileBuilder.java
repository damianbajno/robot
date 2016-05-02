package pl.bookstore.robot.pojo;

import javafx.collections.ObservableList;

/**
 * Created by damian on 22.04.16.
 */
public class ProfileBuilder {
    private static Profile profile;

    public static Profile build(BookStore bookStore, ObservableList<String> categories) {
        profile = new Profile("Category");
//        profile.setBookStore(bookStore);
        profile.addCategories(categories);
        return profile;
    };

    private ProfileBuilder() {
        profile = new Profile("Category");
    }

}
