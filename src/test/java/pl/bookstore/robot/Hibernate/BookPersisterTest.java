package pl.bookstore.robot.Hibernate;
import static org.assertj.core.api.Assertions.*;
import org.hibernate.SessionFactory;
import org.mockito.Mock;
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
        when(bookPersister.closeSession()).thenReturn(true);
        Assert.assertEquals(bookPersister.closeSession(), true);
    }

    @Test
    public void testIfBookStoreIsSavedInDBSuccesfully(){
        BookPersister bookPersister1 = new BookPersister();
        List<BookStore> bookStoresList = new ArrayList<>();
        BookStore bookStore = new BookStore("nexto.pl", "http://www.nexto.pl/", "<a href>", "eTitle", "Crime");
        bookStoresList.add(bookStore);
        bookPersister1.openSession();
        bookPersister1.saveBookStore(bookStoresList);
        bookPersister.closeSession();
        bookPersister.openSession();
        BookStore bookStore1 = bookPersister1.getBookStore(1);
        Assert.assertEquals(bookStoresList.get(0), bookStore1);
    }

    @Test
    public void testIfBookIsSavedInDatabase(){
        BookPersister persistBook = new BookPersister();
        Book book = new Book("KSIONSZKA" ,"To jest dramat k*rwa");
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("KSIONSZKA" ,"To jest dramat k*rwa"));
        persistBook.openSession();
        persistBook.saveBooks(bookList);
        Assert.assertEquals(book.toString(), persistBook.getBook(1));

    }
}
