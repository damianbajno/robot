package pl.bookstore.robot.booksearch;

import org.apache.log4j.Logger;
import pl.bookstore.robot.dao.BookStoreDao;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.HashSet;
import java.util.List;

/**
 * Search books in all BookStores and save them in database.
 * <p>
 * Created by damian on 31.03.16.
 *
 * @author Damian Bajno (pseudo thread, Di)
 */
public class BookSearchEngine{
    private static Logger logger = Logger.getLogger(BookSearchEngine.class);
    private static BookStoreDao bookStoreDao = new BookStoreDao();

    public static void main(String[] args) {
        logger.trace("==== BookSearch Engine started ====");

        List<BookStore> bookStoreList = bookStoreDao.getBookStoreList();

        for (BookStore bookStore : bookStoreList) {
            BookSearchThread bookSearchThread = new BookSearchThread(bookStore);
            bookSearchThread.start();
        }
    }

    private static class BookSearchThread extends Thread {
        private BookStore bookStore;

        public BookSearchThread(BookStore bookStore) {
            this.bookStore = bookStore;
        }

        @Override
        public void run() {
            LinkParserCollector linkParserCollector =new LinkParserCollector(bookStore);
            HashSet<String> linkSet = linkParserCollector.search();

            BookParserCollector bookParserCollector = new BookParserCollector(bookStore);
            List<Book> bookList = bookParserCollector.searchBooksIn(linkSet);
            bookList.forEach(book -> bookStore.addBook(book));

            logger.trace("Started saving bookList from " + bookStore.toString());
            bookStoreDao.update(bookStore);
        }
    }
}

