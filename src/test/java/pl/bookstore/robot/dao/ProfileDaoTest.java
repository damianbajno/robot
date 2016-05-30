package pl.bookstore.robot.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.*;

import java.util.List;

/**
 * Created by damian on 5/2/16.
 */
public class ProfileDaoTest {


    @Test(groups = "NewTest")
    public void ifIPutProfileToTableItWillBeInTable() throws NoCategorySelectedException {
        //given
        ProfileDao profileDao = new ProfileDao();
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("Romance");
        Profile profile = ProfileBuilder.build(categoryList);
        String query = "from Profile";

        //when
        BookStore bookStore = BookStoreContainer.getBookStore(0);
        bookStore.setId(1);
        profileDao.persist(profile, bookStore);
        profile.setBookStore(bookStore);

        //then
        Session session = profileDao.getSession();
        session.beginTransaction();
        Query query1 = session.createQuery(query);
        List<Profile> profileList = query1.list();
        Assertions.assertThat(profileList).contains(profile);

        //clear database
        profileList.forEach(p -> session.delete(p));
        session.getTransaction().commit();


    }
}
