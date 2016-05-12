package pl.bookstore.robot.hibernate;

import org.hibernate.Session;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * Created by damian on 5/12/16.
 */
public class TestHibernate {

    public static void main(String[] args) {
        BookPersister bookPersister=new BookPersister();
        Book book=new Book("title3","Category3");

        bookPersister.openSession();
        List<BookStore> bookStoreList = bookPersister.getBookStores();
        bookPersister.commitSession();


        book.setBookStore(bookStoreList.get(0));

        bookPersister.openSession();
        bookPersister.persistBook(book);
        bookPersister.commitSession();
        bookPersister.closeSessionFactory();

    }

}
