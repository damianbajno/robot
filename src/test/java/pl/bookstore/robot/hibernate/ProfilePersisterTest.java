package pl.bookstore.robot.hibernate;

import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.Profile;

/**
 * Created by damian on 5/2/16.
 */
public class ProfilePersisterTest {


    @Test(groups = "NewTest")
    public void ifIPutProfileToTableItWillBeInTable(){
        //given
        ProfilePersister profilePersister=new ProfilePersister();
        Profile profile = new Profile("Damian1");
        profile.addCategory("a");
        profile.addCategory("b");
        profile.addCategory("c");

        //when
        profilePersister.persistProfile(profile, BookStoreContainer.getBookStore(0));

        //then
        profilePersister.getProfilesFromBookStore(BookStoreContainer.getBookStore(0)).contains(profile);


    }


}
