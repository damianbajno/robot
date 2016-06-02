package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import org.apache.log4j.Logger;
import pl.bookstore.robot.booksearch.wrapper.BookParserWrapper;
import pl.bookstore.robot.booksearch.wrapper.DocumentWrapper;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Search books in Bookstore.
 *
 * @author Damian Bajno (pseudo thread, Di)
 * @see LinkParserCollector
 */
public class BookParserCollector {
    private static Logger logger = Logger.getLogger(BookParserCollector.class);

    private BookStore bookStore;
    private BookParserWrapper bookParserWrapper;
    private List<Book> booksList = new ArrayList<Book>();
    private DocumentWrapper documentWrapper = new DocumentWrapper();

    public BookParserCollector(BookStore bookStore) {
        this.bookStore = bookStore;
        bookParserWrapper = new BookParserWrapper(bookStore);
    }

    /**
     * Search books in BookStore by searching
     * pages and subpages.
     *
     * @param /set of links to search Books
     * @return list of books found in BookStore
     */
    public List<Book> searchBooksIn(Set<String> hyperLinks) {
        logger.trace("Started searching books on site in " + bookStore.getName() + " by thread " + Thread.currentThread().getName());

        Iterator<String> linksIterator = hyperLinks.iterator();

        while (linksIterator.hasNext()) {

            String searchedLink = linksIterator.next();
            try {
                Document document = documentWrapper.getDocumentFrom(searchedLink);
                List<Book> bookList = bookParserWrapper.search(document);
                this.booksList.addAll(bookList);
            } catch (ResponseException e) {
                e.printStackTrace();
                logger.error("Couldn't get document from searchedLink = " + searchedLink);
            }
        }

        logger.trace("End searching books on site in " + bookStore.getName());
        return this.booksList;
    }


    @Override
    public String toString() {
        return "BookParserCollector [" + bookStore.toString() + "]";
    }

}
