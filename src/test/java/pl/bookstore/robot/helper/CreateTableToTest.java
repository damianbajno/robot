package pl.bookstore.robot.helper;

import pl.bookstore.robot.DAO.BookStoreDAO;
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
        BookPersister bookPersister=new BookPersister();
        BookStore[] bookStores=BookStoreContainer.getBookStores();
        Book[] books={new Book("a1", "b1"),new Book("a2", "b2"), new Book("a3", "b3")};

        for (int i = 0; i < bookStores.length; i++) {
            bookStores[i].addBook(books[i]);
        }

        bookPersister.openSession();

        for (int i = 0; i < bookStores.length; i++) {
            bookPersister.saveBookStore(bookStores[i]);
        }

        bookPersister.commitSession();
        bookPersister.closeSessionFactory();
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

    public static void sendBookStoreToDatabases() {
        BookStore bookStoreBookrix = new BookStore("bookrix", "http://www.bookrix.com/books.html", "<div class=item-content>", "<a class=word-break>", "<ul class=item-details>" + "<li>");
        BookStore bookStoreGoodreads = new BookStore("goodreads", "https://www.goodreads.com/genres/business", "<div class=description descriptionContainer>", "<a class=bookTitle>", "brak");
        BookStore bookStoreGutenberg = new BookStore("gutenberg", "https://www.gutenberg.org/ebooks/searchBooks/?query=free+book&go=Go", "<div class=header>", "<h1 itemprop=name>", "brak");

        BookStoreDAO bookStoreDAO = new BookStoreDAO();
        bookStoreDAO.persist(bookStoreBookrix);
        bookStoreDAO.persist(bookStoreGoodreads);
        bookStoreDAO.persist(bookStoreGutenberg);
    }

    public static void printDatabasesBookStores() {
        BookStoreDAO bookStoreDAO = new BookStoreDAO();
        List<BookStore> bookStores = bookStoreDAO.getBookStores();

        Iterator<BookStore> iterator = bookStores.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
