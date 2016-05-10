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
    private static Profile profile;

    /**
     * Create profile from given categories, profile name is created from first letters of categories.
     *
     * @param categories list of categories
     * @return instance of class profile
     */

    public static Profile build(ObservableList<String> categories) {
        StringBuilder name=new StringBuilder();
        categories.stream().map(c -> c.charAt(0)).forEach(b -> name.append(b));
        profile = new Profile(name.toString());
        profile.addCategories(categories);
        return profile;
    }

}
