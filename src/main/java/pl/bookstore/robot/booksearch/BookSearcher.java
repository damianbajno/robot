package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Class is designed to search books in bookstore,
 * has three methods: following given link,
 * within HTML document and on every page in bookstore.
 *
 * @author Damian Bajno (pseudo thread, Di)
 * @see LinkSearch
 */

public class BookSearcher {
    private static Logger logger = Logger.getLogger(BookSearcher.class);

    private BookStore bookStore;
    private List<Book> booksList;

    /**
     * @param bookStore specify where to search books, and on what tags to search
     */
    public BookSearcher(BookStore bookStore) {
        this.bookStore = bookStore;
        this.booksList = new ArrayList<Book>();
    }

    /**
     * Search books in BookStore by searching
     * the sub pages in specified link
     *
     * @return list of books found in BookStore
     */

    public List<Book> searchBooks() {
        HashSet<String> hyperLinks = searchLinksOnSite();

        hyperLinks.stream().forEach(s -> booksList.addAll(searchBooks(s)));
        return booksList;
    }

    /**
     * @return HashSet with sub pages on specify link
     */

    HashSet<String> searchLinksOnSite() {
        LinkSearch linkSearch = new LinkSearch(bookStore);
        return linkSearch.searchHyperlinksOnPageAndSubPages();
    }

    /**
     * @param link where books are search
     * @return list of books found in link
     */

    public List<Book> searchBooks(String link) {
        try {
            UserAgent userAgent = new UserAgent();
            logger.info("Searching book on site = " + link);
            Document document = userAgent.visit(link);
            return searchBooks(document);
        } catch (ResponseException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return Collections.emptyList();
    }

    /**
     * @param document where books are search
     * @return list of books found in document
     */
//FIXME: use Anti-Corruption Layer to insulate against Jaunt API
    public List<Book> searchBooks(Document document) {
        ArrayList<Book> books = new ArrayList<>();

        Steps toTitle = Steps.from(BookSearcherUtils.getPathToElement(bookStore.getSearchForTitle()));
        Steps toCategory = Steps.from(BookSearcherUtils.getPathToElement(bookStore.getSearchForCategory()));

        Elements titleElements = document.findEvery(toTitle.nextItem());
        Elements categoryElements = document.findEvery(toCategory.nextItem());

        String title = null;
        String previousTitle = "";
        for (int i = 0; i < titleElements.size(); i++) {
            Book book = null;
            String category = "brak";
            try {
                title = searchElement(titleElements.getElement(i), toTitle);
                category = searchElement(categoryElements.getElement(i), toCategory);
            } catch (NotFound e) {
                logger.info("Did not found any Books on site "+ document.getUrl());
            } finally {
                if (!previousTitle.equals(title)) {
                    book = new Book(title, category, bookStore);
                    books.add(book);
                    logger.info("Found book "+book);
                }
                previousTitle=title;
            }

        }
        return books;
    }

    /**
     *
     * @param element where to search e.g. title, category
     * @param steps path to searched text e.g. title, category
     * @return text searched in element
     */

    private String searchElement(Element element, Steps steps) {
        while(steps.hasNext()) {
            Elements elems = element.findEach(steps.nextItem());
            try {
                 element = elems.getElement(steps.nextCount());
            } catch (NotFound notFound) {
                logger.error("Search for title/category failed: " + notFound.getMessage());
            } finally {
                System.out.println("FINALLY: "+element.getText());
                return element.getText().trim();
            }
        }
        System.out.println("RETURN: "+element.getText());
        return element.getText().trim();
    }

    @Override
    public String toString() {
        return "BookSearcher [" + bookStore.toString() + "]";
    }
}
