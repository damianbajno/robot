package pl.bookstore.robot.pojo;

import javafx.collections.ObservableList;

/**
 * Created by damian on 22.04.16.
 */
public class ProfileBuilder {
    private static Profile profile;

    /**
     * Create profile from given categories, profile name is created from first letters of categories.
     *
     * @param categories list of categories
     * @return instance of class profile
     */

    public static Profile build(ObservableList<String> categories) {
        profile = new Profile();
        profile.addCategories(categories);
        return profile;
    }

}
