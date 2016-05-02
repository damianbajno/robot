package pl.bookstore.robot.helper;

import pl.bookstore.robot.hibernate.BookPersister;
import pl.bookstore.robot.hibernate.ProfilePersister;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.pojo.Profile;
import pl.bookstore.robot.pojo.ProfileBuilder;

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
        BookPersister bookPersister=new BookPersister();
        BookStore[] bookStores=BookStoreContainer.getBookStores();
        Book[] books={new Book("a1", "b1"),new Book("a2", "b2"), new Book("a3", "b3")};

        for (int i = 0; i < books.length; i++) {
            books[i].setBookStore(bookStores[i]);
        }

        bookPersister.openSession();

        for (int i = 0; i < bookStores.length; i++) {
            bookPersister.saveBookStore(bookStores[i]);
        }

        bookPersister.commitSession();
        bookPersister.closeSessionFactory();
    }

    public static void createTableProfile(){
        ProfilePersister profilePersister=new ProfilePersister();

        Profile profile = new Profile("Damian");
        profilePersister.persistProfile(profile, BookStoreContainer.getBookStore(0));
    }

    public static void printDatabasesBookStoresHibernate() {
        BookPersister bookPersister=new BookPersister();
        bookPersister.openSession();
        List<BookStore> bookStores = bookPersister.getBookStores();
        bookPersister.closeSessionFactory();

        Iterator<BookStore> iterator = bookStores.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
