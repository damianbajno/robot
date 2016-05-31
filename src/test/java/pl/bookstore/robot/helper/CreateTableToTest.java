package pl.bookstore.robot.helper;

import pl.bookstore.robot.dao.BookStoreDao;
import pl.bookstore.robot.dao.Dao;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.Iterator;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class CreateTableToTest {
    private static BookStoreDao bookStoreDAO = new BookStoreDao();

    public static void main(String[] args) {

        CreateTableToTest.sendBookStoreAndBooksToDatabasesHibernate();
    }

    private static void sendBookStoreAndBooksToDatabasesHibernate() {

        BookStore[] bookStores = BookStoreContainer.getBookStores();
        Book[] books = {new Book("a1", "b1"), new Book("a2", "b2"), new Book("a3", "b3"),
                new Book("a4", "b4"), new Book("a5", "b5")};

        bookStores[0].addBook(books[0]);
        bookStores[1].addBook(books[1]);
        bookStores[2].addBook(books[2]);
        bookStores[2].addBook(books[3]);
        bookStores[2].addBook(books[4]);


        bookStoreDAO.persist(bookStores[0]);
        bookStoreDAO.persist(bookStores[1]);
        bookStoreDAO.persist(bookStores[2]);

        Dao.closeSessionFactory();
    }

    public static void printDatabasesBookStoresHibernate() {
        List<BookStore> bookStores = bookStoreDAO.getBookStoreList();

        Iterator<BookStore> iterator = bookStores.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Dao.closeSessionFactory();
    }

}
