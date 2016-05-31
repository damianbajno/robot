package pl.bookstore.robot.dao;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * Created by damian on 5/30/16.
 */
public class BookStoreDaoTest {

    @Test(groups = "IntegrationTest")
    public void testIfBookStoreIsSavedDeletedAndReceivedInDB() {
        //given
        BookStoreDao bookStoreDao = new BookStoreDao();
        BookStore bookStoreExpected = new BookStore("nexto.pl", "http://www.nexto.pl/", "eTitle", "Crime");

        //when
        bookStoreDao.persist(bookStoreExpected);
        List<BookStore> bookStoresRetrievedFromDB = bookStoreDao.getBookStoreList();

        //then
        Assertions.assertThat(bookStoresRetrievedFromDB).contains(bookStoreExpected);

        //cleaning database
        bookStoreDao.delete(bookStoreExpected);
    }


}
