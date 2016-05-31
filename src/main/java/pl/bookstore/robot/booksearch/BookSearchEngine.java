package pl.bookstore.robot.booksearch;

import org.apache.log4j.Logger;
import pl.bookstore.robot.dao.BookStoreDao;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * Search books in all BookStores and save them in database.
 * <p>
 * Created by damian on 31.03.16.
 *
 * @author Damian Bajno (pseudo thread, Di)
 */
public class BookSearchEngine extends Thread {
    private static Logger logger = Logger.getLogger(BookSearchEngine.class);
    private static BookStoreDao bookStoreDao = new BookStoreDao();
    private BookStore bookStore;

    public BookSearchEngine(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    public static void main(String[] args) {
        logger.info("==== BookSearch Engine started ====");

        List<BookStore> bookStoreList = bookStoreDao.getBookStoreList();

        for (BookStore bookStore : bookStoreList) {
            BookSearchEngine bookSearchEngine=new BookSearchEngine(bookStore);
            bookSearchEngine.start();
        }
    }


    @Override
    public void run() {
        BookSearcher bookSearcher = new BookSearcher(bookStore);
        List<Book> bookList = bookSearcher.searchBooks();
        bookList.forEach(book -> bookStore.addBook(book));

        logger.info("Started saving bookList from "+ bookStore.toString());
        bookStoreDao.update(bookStore);
    }
}

