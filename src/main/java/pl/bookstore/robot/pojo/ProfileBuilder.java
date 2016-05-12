package pl.bookstore.robot.pojo;

import javafx.collections.ObservableList;

/**
 * Created by damian on 22.04.16.
 * Class for creating new book profiles
 * @author Stycz
 * @version 1.0
 *
 */
public class ProfileBuilder {

    /**
     * Create profile from given categories, profile name is created from first letters of categories.
     *
     * @param categories list of categories
     * @return instance of class profile
     */

    public static Profile build(ObservableList<String> categories) throws NoCategorySelectedException {
        if (categories.isEmpty()) throw new NoCategorySelectedException("No category was selected");
        Profile profile = new Profile();
        profile.addCategories(categories);
        return profile;
    }

}
