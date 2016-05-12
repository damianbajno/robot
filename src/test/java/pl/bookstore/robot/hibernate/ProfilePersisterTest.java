package pl.bookstore.robot.hibernate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.NoCategorySelectedException;
import pl.bookstore.robot.pojo.Profile;
import pl.bookstore.robot.pojo.ProfileBuilder;

import java.util.List;

/**
 * Created by damian on 5/2/16.
 */
public class ProfilePersisterTest {


    @Test(groups = "NewTest")
    public void ifIPutProfileToTableItWillBeInTable(){
        //given
        ProfilePersister profilePersister=new ProfilePersister();
        Profile profile = null;
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("Romance");

        try {
            profile = ProfileBuilder.build(categoryList);
        } catch (NoCategorySelectedException e) {
            e.printStackTrace();
        }

        //when
        profilePersister.persistProfile(profile, BookStoreContainer.getBookStore(0));
        profile.setBookStore(BookStoreContainer.getBookStore(0));

        //then
        List<Profile> retrievedProfile = profilePersister.getProfilesFromBookStore(BookStoreContainer.getBookStore(0));
        Assertions.assertThat(retrievedProfile.get(0)).isEqualTo(profile);

    }
}
