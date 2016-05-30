package pl.bookstore.robot.hibernate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.NoCategorySelectedException;
import pl.bookstore.robot.pojo.Profile;
import pl.bookstore.robot.pojo.ProfileBuilder;

/**
 * Created by damian on 5/2/16.
 */
public class ProfilePersisterTest {


    @Test(groups = "NewTest")
    public void ifIPutProfileToTableItWillBeInTable(){
        //given
        ProfileDAO profilePersister=new ProfileDAO();
        Profile profile = null;
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("Romance");

        try {
            profile = ProfileBuilder.build(categoryList);
        } catch (NoCategorySelectedException e) {
            e.printStackTrace();
        }

        //when
        profilePersister.persist(profile, BookStoreContainer.getBookStore(0));
        profile.setBookStore(BookStoreContainer.getBookStore(0));

        //then
//        List<Profile> retrievedProfile = profilePersister.getProfilesFromBookStore(BookStoreContainer.getBookStore(0));
//        Assertions.assertThat(retrievedProfile.get(0)).isEqualTo(profile);

    }
}
