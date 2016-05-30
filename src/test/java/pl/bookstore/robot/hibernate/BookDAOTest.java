package pl.bookstore.robot.hibernate;

import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by stycz0007 on 14.04.16.
 */
public class BookDAOTest {
//
//    @Test
//    public void testIfSessionIsOpenedCorrectly() {
//        BookDAO bookDAO = Mockito.mock(BookDAO.class);
//        when(bookDAO.openSession()).thenReturn(true);
//        Assert.assertEquals(bookDAO.openSession(), true);
//    }
//
//    @Test
//    public void testIfSessionIsClosedCorrectly() {
//        BookDAO bookDAO = Mockito.mock(BookDAO.class);
//        when(bookDAO.closeSessionFactory()).thenReturn(true);
//        Assert.assertEquals(bookDAO.closeSessionFactory(), true);
//    }
//
//    @Test(groups = "IntegrationTest")
//    public void testIfBookStoreIsSavedInDB() {
//        //given
//        BookDAO bookDAO = new BookDAO();
//        BookStore bookStoreExpected = new BookStore("nexto.pl", "http://www.nexto.pl/", "eTitle", "Crime");
//
//        //when
//
//        bookDAO.openSession();
//        bookDAO.persistBookStore(bookStoreExpected);
//        bookDAO.commitSession();
//
//        bookDAO.openSession();
//        List<BookStore> bookStoresRetrievedFromDB = bookDAO.getBookStores();
//
//        //then
//        Assertions.assertThat(bookStoresRetrievedFromDB).contains(bookStoreExpected);
//        bookDAO.commitSession();
//
//        //cleaning database
//        bookDAO.openSession();
//        bookDAO.delete(bookStoreExpected);
//        bookDAO.commitSession();
//        bookDAO.closeSessionFactory();
//    }

//    @Test(groups = "IntegrationTest")
//    public void testIfBookIsSavedInDatabase() {
//        //given
//        BookDAO persist = new BookDAO();
//        Book bookExpected = new Book("KSIONSZKA", "To jest dramat k*rwa");
//
//        //when
//        persist.openSession();
//        persist.persist(bookExpected);
//        persist.commitSession();
//
//        //then
//        persist.openSession();
//        Assertions.assertThat(persist.getBooks()).contains(bookExpected);
//        persist.commitSession();
//
//        //clear database
//        persist.openSession();
//        persist.deleteBook(bookExpected);
//        persist.commitSession();
//
//    }


//    @Test
//    public void testIfSavedBookHasConnectionWithBookStore() {
//        //given
//        BookDAO bookPersister = new BookDAO();
//        Book bookExpected = new Book("KSIONSZKA", "To jest dramat k*rwa");
//        BookStore bookStoreExpected = new BookStore("nexto.pl", "http://www.nexto.pl/", "eTitle", "Crime");
//        bookExpected.setBookStore(bookStoreExpected);
//
//        //when
//        bookPersister.openSession();
//        bookPersister.persist(bookStoreExpected);
//        bookPersister.saveBook(bookExpected);
//        bookPersister.commitSession();
//
//        //then
//        bookPersister.openSession();
//        List<Book> books = bookPersister.getBookFromBookStore(bookStoreExpected);
//        Assertions.assertThat(books).contains(bookExpected);
//        bookPersister.commitSession();
//
//        //clear database
//        bookPersister.openSession();
//        bookPersister.deleteBook(bookExpected);
//        bookPersister.commitSession();
//
//    }
}
