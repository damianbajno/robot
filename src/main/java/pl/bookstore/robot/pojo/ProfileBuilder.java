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

    public static Profile build(ObservableList<String> categories) {
        StringBuilder name=new StringBuilder();
        categories.stream().map(c -> c.charAt(0)).forEach(b -> name.append(b));
        profile = new Profile(name.toString());
        profile.addCategories(categories);
        return profile;
    }

}
