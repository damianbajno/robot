package pl.bookstore.robot.helper;

import pl.bookstore.robot.hibernate.BookDAO;
import pl.bookstore.robot.hibernate.BookStoreDao;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.Iterator;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class CreateTableToTest {
    private static BookStoreDao bookStoreDAO = new BookStoreDao();
    private static BookDAO bookDAO = new BookDAO();

    public static void main(String[] args) {

        CreateTableToTest.sendBookStoreAndBooksToDatabasesHibernate();
    }

    public static void sendBookStoreAndBooksToDatabasesHibernate() {

        BookStore[] bookStores = BookStoreContainer.getBookStores();
        Book[] books = {new Book("a1", "b1"), new Book("a2", "b2"), new Book("a3", "b3"),
                new Book("a4", "b4"), new Book("a5", "b5")};

        books[0].setBookStore(bookStores[0]);
        books[1].setBookStore(bookStores[1]);
        books[2].setBookStore(bookStores[2]);
        books[3].setBookStore(bookStores[2]);
        books[4].setBookStore(bookStores[2]);


        bookStoreDAO.persist(bookStores[0]);
        bookStoreDAO.persist(bookStores[1]);
        bookStoreDAO.persist(bookStores[2]);

        bookDAO.persist(books[0]);
        bookDAO.persist(books[1]);
        bookDAO.persist(books[2]);
    }

    public static void printDatabasesBookStoresHibernate() {
        List<BookStore> bookStores = bookStoreDAO.getBookStoreList();

        Iterator<BookStore> iterator = bookStores.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
