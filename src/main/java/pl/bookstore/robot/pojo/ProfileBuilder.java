package pl.bookstore.robot.pojo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

/**
 * Created by damian on 22.04.16.
 * Class for creating new book profiles
 *
 * @author Stycz
 * @version 1.0
 */
public class ProfileBuilder {

    /**
     * Create profile from given categories, profile name is created from first letters of categories.
     *
     * @param categories list of categories
     * @return instance of class profile
     * @throws NoCategorySelectedException when any category was selected in list
     */
    public static Profile build(ObservableList<String> categories) throws NoCategorySelectedException {
        if (categories.isEmpty()) throw new NoCategorySelectedException("No category was selected");
        Profile profile = new Profile();
        profile.addCategories(categories);
        return profile;
    }

    protected static Profile build(String... categories) throws NoCategorySelectedException {
        if (categories.length == 0) throw new NoCategorySelectedException("No category was selected");

        ObservableList<String> categoriesList = FXCollections.observableList(Arrays.asList(categories));

        Profile profile = new Profile();
        profile.addCategories(categoriesList);
        return profile;
    }

}
