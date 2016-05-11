package pl.bookstore.robot.pojo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * Created by damian on 5/11/16.
 */
public class ProfileTest {

    @Test(groups = "NewTest")
    public void testIfProfilesAreEqualIfTheyAreEmpty() {
        //given
        Profile profileFirst = new Profile();
        Profile profileSecond = new Profile();

        //when
        boolean isEquals = profileFirst.equals(profileSecond);

        Assertions.assertThat(isEquals).isTrue();
    }

    @Test(groups = "NewTest")
    public void testIfProfileCategoriesAreEqualProfilesAreEqual() {
        //given
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Comedy");
        Profile profileFirst = new Profile();
        profileFirst.addCategories(observableList);
        Profile profileSecond = new Profile();
        profileSecond.addCategories(observableList);

        //when
        boolean isEquals = profileFirst.equals(profileSecond);

        //then
        Assertions.assertThat(isEquals).isTrue();
    }

    @Test(groups = "NewTest")
    public void testIfProfileCategoriesAreDifferentProfilesAreEqual() {
        //given
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("Comedy");
        Profile profileFirst = new Profile();
        profileFirst.addCategories(observableList);
        observableList.add("Music");
        Profile profileSecond = new Profile();
        profileFirst.addCategories(observableList);

        //when
        boolean isEquals = profileFirst.equals(profileSecond);

        //then
        Assertions.assertThat(isEquals).isFalse();
    }
}
