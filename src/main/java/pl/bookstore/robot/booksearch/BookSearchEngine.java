package pl.bookstore.robot.booksearch;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.BookStore;

/**
 * Created by damian on 31.03.16.
 */
public class BookSearchEngine {
    static Logger logger = Logger.getLogger(BookSearchEngine.class);

    public static void main(String[] args) {
        logger.info("==== Book engine started ====");

//        BookStoreDAO bookStoreDAO = new BookStoreDAO();
//        List<BookStore> bookStores = bookStoreDAO.getBookStores();

//        logger.info("BookStores List = "+bookStores.toString());

        BookStore bookStoreGoodreads = new BookStore("goodreads", "https://www.goodreads.com/genres/business", "<div class=description descriptionContainer>", "<a class=bookTitle>", "brak");


        BookSearch bookSearch = new BookSearch(bookStoreGoodreads);
        try {
            bookSearch.searchBooks();
        } catch (ResponseException e) {
            e.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }
}
