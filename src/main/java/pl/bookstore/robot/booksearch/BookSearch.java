package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.DAO.BookDAO;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.*;

/**
 *
 * Class is designed to search books in books store,
 * you have three methods to search books: on link,
 * document, on every page in bookstore.
 *
 * @author Damian Bajno (pseudo thread, Di)
 * @see LinkSearch
 */

public class BookSearch {
    private static Logger logger = Logger.getLogger(BookSearch.class);

    private BookStore bookStore;
    private List<Book> booksList;
    private BookDAO bookDAO;

    /**
     * @param bookStore specify where to search books, and on what tags to search
     */
    public BookSearch(BookStore bookStore) {
        this.bookStore = bookStore;
        this.booksList=new ArrayList<Book>();
        this.bookDAO=new BookDAO();
    }

    /**
     *  Search books in BookStore by searching
     *  the sub pages in specified link
     *
     * @return list of books found in BookStore
     */

    public List<Book> searchBooks() {
        HashSet<String> hyperLinks = searchLinksOnSite();
        Iterator<String> linksIterator = hyperLinks.iterator();

        while (linksIterator.hasNext()) {
            searchBooksOnSite(linksIterator.next());
        }

        return this.booksList;
    }

    /**
     *
     * @return HashSet with sub pages on specify link
     */

    HashSet<String> searchLinksOnSite() {
        LinkSearch linkSearch = new LinkSearch(bookStore);
        return linkSearch.searchHyperlinksOnSiteAndSubsites();
    }

    /**
     *
     * @param link where books are search
     * @return list of books found in link
     */

    public List<Book> searchBooksOnSite(String link) {
        try {
            UserAgent userAgent = new UserAgent();
            logger.info("Searching book on site = "+link);
            Document document = userAgent.visit(link);
            return searchBooks(document);
        } catch (ResponseException e) {
            logger.error(e.getStackTrace().toString());
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param document where books are search
     * @return list of books found in document
     */

    public List<Book> searchBooks (Document document){
            Elements bookElements = document.findEvery(this.bookStore.getSearchForBook());

            Iterator<Element> bookElementsIterator = bookElements.iterator();
            while (bookElementsIterator.hasNext()) {

                try {
                    Element bookElement = bookElementsIterator.next();
                    Element elementTitle = bookElement.findFirst(this.bookStore.getSearchForTitle());

                    String searchForCategory = this.bookStore.getSearchForCategory();

                    Book book;
                    if (searchForCategory != "brak") {

                        Element categoryElements = bookElement.findFirst(this.bookStore.getSearchForCategory()).findFirst("<li>");
                        book = new Book(elementTitle.getText().trim(), categoryElements.getText().trim(), this.bookStore);
                    } else {
                        book = new Book(elementTitle.getText().trim(), "brak", this.bookStore);
                    }

                    this.booksList.add(book);
                    bookDAO.persist(book);
                    logger.info("Book added to database " + book.toString());
                } catch (NotFound notFound) {
                    logger.error(notFound.getMessage());

                }

            }
            return this.booksList;
        }


        @Override
        public String toString () {
            return "BookSearch [" + bookStore.toString() + "]";
        }
}
