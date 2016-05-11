package pl.bookstore.robot.pojo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by damian on 5/11/16.
 */
public class ProfileBuilderTest {

    @Test
    public void ifIBuildProfileItWillHaveAllCategories() {
        //given
        ObservableList<String> givenCategories = FXCollections.observableArrayList();
        givenCategories.add("Romance");
        givenCategories.add("Education");

        Profile expectedProfile = new Profile();
        expectedProfile.addCategories(givenCategories);

        //when
        Profile profile = null;
        try {
            profile = ProfileBuilder.build(givenCategories);
        } catch (NoCategorySelectedException e) {
            e.printStackTrace();
        }

        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void ifITryToBuildProfileWithoutAnyCategoryItThrowsExceptionNoCategorySelected() {
        //given
        ObservableList<String> listWithoutAnyCategory = FXCollections.observableArrayList();

        //then
        Assertions.assertThatExceptionOfType(NoCategorySelectedException.class).
                isThrownBy(() -> ProfileBuilder.build(listWithoutAnyCategory));
    }

}
