package pl.bookstore.robot.booksearch;

import org.apache.log4j.Logger;
import pl.bookstore.robot.hibernate.BookPersister;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * Class which search books stores for books
 * <p>
 * Created by damian on 31.03.16.
 *
 * @author Damian Bajno (pseudo thread, Di)
 */
public class BookSearchEngine {
    static Logger logger = Logger.getLogger(BookSearchEngine.class);

    public static void main(String[] args) {
        logger.info("==== Book engine started ====");

        BookPersister bookPersister = new BookPersister();
        bookPersister.openSession();
        List<BookStore> bookStores = bookPersister.getBookStores();
        bookPersister.commitSession();


        BookStore bookStore = bookStores.get(2);
        BookSearcher bookSearcher = new BookSearcher(bookStore);
        List<Book> books = bookSearcher.searchBooks();
        books.forEach(book -> book.setBookStore(bookStore));


        logger.info("Started saving books");
        bookPersister.openSession();
        bookPersister.saveBooks(books);
        bookPersister.commitSession();

    }
}
