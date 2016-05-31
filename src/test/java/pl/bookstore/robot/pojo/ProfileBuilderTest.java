package pl.bookstore.robot.pojo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * Created by damian on 5/11/16.
 */
public class ProfileBuilderTest {

    @Test
    public void ifIBuildProfileItWillHaveAllCategories() throws NoCategorySelectedException {
        //given

        //when
        Profile profile = null;
        try {
            profile = ProfileBuilder.build("Romance", "Education");
        } catch (NoCategorySelectedException e) {
            e.printStackTrace();
        }

        //then
        Assertions.assertThat(profile.getCategories()).isEqualTo(Arrays.asList("Romance", "Education"));
    }

    @Test
    public void ifITryToCreateProfileWithoutAnyCategoryItThrowsExceptionNoCategorySelected() {
        //given
        ObservableList<String> listWithoutAnyCategory = FXCollections.observableArrayList();

        //then
        Assertions.assertThatExceptionOfType(NoCategorySelectedException.class).
                isThrownBy(() -> ProfileBuilder.build(listWithoutAnyCategory));
    }

}
