package pl.bookstore.robot.hibernate;

import javafx.collections.FXCollections;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.NoCategorySelectedException;
import pl.bookstore.robot.pojo.Profile;
import pl.bookstore.robot.pojo.ProfileBuilder;

/**
 * Created by damian on 5/2/16.
 */
public class ProfilePersisterTest {


    @Test(enabled = false)
    public void ifIPutProfileToTableItWillBeInTable(){
        //given
        ProfilePersister profilePersister=new ProfilePersister();
        Profile profile = null;

        try {
            profile = ProfileBuilder.build(FXCollections.observableArrayList());
        } catch (NoCategorySelectedException e) {
            e.printStackTrace();
        }

        profile.addCategory("a");
        profile.addCategory("b");
        profile.addCategory("c");

        //when
        profilePersister.persistProfile(profile, BookStoreContainer.getBookStore(0));

        //then
        profilePersister.getProfilesFromBookStore(BookStoreContainer.getBookStore(0)).contains(profile);

    }


}
