package pl.bookstore.robot.hibernate;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by stycz0007 on 14.04.16.
 */
public class BookPersisterTest {
    BookPersister bookPersister = Mockito.mock(BookPersister.class);


    @Test
    public void testIfSessionIsOpenedCorreclty(){
        when(bookPersister.openSession()).thenReturn(true);
        Assert.assertEquals(bookPersister.openSession(), true);
    }

    @Test
    public void testIfSessionIsClosedCorreclty(){
        when(bookPersister.closeSessionFactory()).thenReturn(true);
        Assert.assertEquals(bookPersister.closeSessionFactory(), true);
    }

    @Test (groups = "NewTest")
    public void testIfBookStoreIsSavedInDB(){
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
        //cleaning in database
        bookPersister.openSession();
        bookPersister.delete(bookStoreExpected);
        bookPersister.closeSessionFactory();
    }

    @Test (groups = "NewTest")
    public void testIfBookIsSavedInDatabase(){
        BookPersister persistBook = new BookPersister();
        Book book = new Book("KSIONSZKA" ,"To jest dramat k*rwa");
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("KSIONSZKA" ,"To jest dramat k*rwa"));
        persistBook.openSession();
        persistBook.saveBooks(bookList);
        Assert.assertEquals(book.toString(), persistBook.getBook("1"));

    }
}
