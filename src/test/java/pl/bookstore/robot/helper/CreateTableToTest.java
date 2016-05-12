package pl.bookstore.robot.helper;

import pl.bookstore.robot.hibernate.BookPersister;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.Iterator;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class CreateTableToTest {

    public static void main(String[] args) {

        CreateTableToTest.sendBookStoreAndBooksToDatabasesHibernate();
    }

    public static void sendBookStoreAndBooksToDatabasesHibernate() {
        BookPersister bookPersister = new BookPersister();
        BookStore[] bookStores = BookStoreContainer.getBookStores();
        Book[] books = {new Book("a1", "b1"), new Book("a2", "b2"), new Book("a3", "b3")};

        books[0].setBookStore(bookStores[0]);
        books[1].setBookStore(bookStores[1]);
        books[2].setBookStore(bookStores[2]);

        bookPersister.openSession();

        bookPersister.persistBook(books[0]);
        bookPersister.persistBook(books[1]);
        bookPersister.persistBook(books[2]);

        bookPersister.commitSession();
        bookPersister.closeSessionFactory();
    }

    public static void printDatabasesBookStoresHibernate() {
        BookPersister bookPersister = new BookPersister();
        bookPersister.openSession();
        List<BookStore> bookStores = bookPersister.getBookStores();
        bookPersister.closeSessionFactory();

        Iterator<BookStore> iterator = bookStores.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
