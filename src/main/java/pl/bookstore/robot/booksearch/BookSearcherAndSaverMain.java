package pl.bookstore.robot.booksearch;

import org.apache.log4j.Logger;
import pl.bookstore.robot.dao.BookStoreDao;
import pl.bookstore.robot.pojo.BookStore;

import java.util.Arrays;
import java.util.List;

/**
 * Search books in all BookStores and save them in database.
 * <p>
 * Created by damian on 31.03.16.
 *
 * @author Damian Bajno (pseudo thread, Di)
 */
public class BookSearcherAndSaverMain {
    private static Logger logger = Logger.getLogger(BookSearcherAndSaverMain.class);
    private static BookStoreDao bookStoreDao = new BookStoreDao();

    public static void main(String[] args) {
        logger.trace("==== BookSearch Engine started ====");

        List<BookStore> bookStoreList = bookStoreDao.getBookStoreList();

        for (BookStore bookStore : bookStoreList) {
            BookSearcherAndSaverRunnable bookSearcherAndSaverRunnable = new BookSearcherAndSaverRunnable(bookStore);
            Thread bookSearcherAndSaverThread=new Thread(bookSearcherAndSaverRunnable);
            bookSearcherAndSaverThread.start();
        }
    }

    public static class BookSearcherAndSaverRunnable implements Runnable {
        private BookSearcher bookSearcher;
        private BookStore bookStore;

        public BookSearcherAndSaverRunnable(BookStore bookStore) {
            this.bookStore = bookStore;
            this.bookSearcher = new BookSearcher(bookStore);
        }

        @Override
        public void run() {
            bookSearcher.searchRecursivelyIn(Arrays.asList(bookStore.getUrl()));
        }
    }
}

