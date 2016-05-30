package pl.bookstore.robot.booksearch;

import org.apache.log4j.Logger;
import pl.bookstore.robot.hibernate.BookDAO;
import pl.bookstore.robot.hibernate.BookStoreDao;
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
        logger.info("==== BookSearch Engine started ====");

        BookDAO bookDAO = new BookDAO();
        BookStoreDao bookStoreDao=new BookStoreDao();
        List<BookStore> bookStoreList = bookStoreDao.getBookStoreList();

        for (BookStore bookStore : bookStoreList) {
            BookSearcher bookSearcher = new BookSearcher(bookStore);
            List<Book> bookList = bookSearcher.searchBooks();
            bookList.forEach(book -> book.setBookStore(bookStore));


            logger.info("Started saving bookList");
            bookDAO.persistBookList(bookList);
        }


    }
}
