package pl.bookstore.robot.hibernate;

import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

/**
 * Created by stycz0007 on 14.04.16.
 */
public class BookPersisterTest {

    @Test
    public void testIfSessionIsOpenedCorrectly() {
        BookPersister bookPersister = Mockito.mock(BookPersister.class);
        when(bookPersister.openSession()).thenReturn(true);
        Assert.assertEquals(bookPersister.openSession(), true);
    }

    @Test
    public void testIfSessionIsClosedCorrectly() {
        BookPersister bookPersister = Mockito.mock(BookPersister.class);
        when(bookPersister.closeSessionFactory()).thenReturn(true);
        Assert.assertEquals(bookPersister.closeSessionFactory(), true);
    }

    @Test(groups = "IntegrationTest")
    public void testIfBookStoreIsSavedInDB() {
        //given
        BookPersister bookPersister = new BookPersister();
        BookStore bookStoreExpected = new BookStore("nexto.pl", "http://www.nexto.pl/", "<a href>", "eTitle", "Crime");

        //when

        bookPersister.openSession();
        bookPersister.saveBookStore(bookStoreExpected);
        bookPersister.commitSession();

        bookPersister.openSession();
        List<BookStore> bookStoresRetrievedFromDB = bookPersister.getBookStores();

        //then
        Assertions.assertThat(bookStoresRetrievedFromDB).contains(bookStoreExpected);
        bookPersister.commitSession();

        //cleaning database
        bookPersister.openSession();
        bookPersister.deleteBookStore(bookStoreExpected);
        bookPersister.commitSession();
        bookPersister.closeSessionFactory();
    }

    @Test(groups = "IntegrationTest")
    public void testIfBookIsSavedInDatabase() {
        //given
        BookPersister persistBook = new BookPersister();
        Book bookExpected = new Book("KSIONSZKA", "To jest dramat k*rwa");

        //when
        persistBook.openSession();
        persistBook.saveBook(bookExpected);
        persistBook.commitSession();

        //then
        persistBook.openSession();
        Assertions.assertThat(persistBook.getBooks()).contains(bookExpected);
        persistBook.commitSession();

        //clear database
        persistBook.openSession();
        persistBook.deleteBook(bookExpected);
        persistBook.commitSession();

    }


    @Test
    public void testIfSavedBookHasConnectionWithBookStore() {
        //given
        BookPersister bookPersister = new BookPersister();
        Book bookExpected = new Book("KSIONSZKA", "To jest dramat k*rwa");
        BookStore bookStoreExpected = new BookStore("nexto.pl", "http://www.nexto.pl/", "<a href>", "eTitle", "Crime");
        bookExpected.setBookStore(bookStoreExpected);
        
        //when
        bookPersister.openSession();
        bookPersister.saveBookStore(bookStoreExpected);
        bookPersister.saveBook(bookExpected);
        bookPersister.commitSession();

        //then
        bookPersister.openSession();
        List<Book> books = bookPersister.getBookFromBookStore(bookStoreExpected);
        Assertions.assertThat(books).contains(bookExpected);
        bookPersister.commitSession();

        //clear database
        bookPersister.openSession();
        bookPersister.deleteBook(bookExpected);
        bookPersister.commitSession();

    }

}
