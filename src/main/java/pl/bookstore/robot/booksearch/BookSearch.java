package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.hibernate.BookPersister;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.*;

/**
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
    private BookPersister bookPersister;

    /**
     * @param bookStore specify where to search books, and on what tags to search
     */
    public BookSearch(BookStore bookStore) {
        this.bookStore = bookStore;
        this.booksList = new ArrayList<Book>();
        this.bookPersister = new BookPersister();
    }

    /**
     * Search books in BookStore by searching
     * the sub pages in specified link
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

    public List<Book> searchBooksOnSite(String link) {
        try {
            UserAgent userAgent = new UserAgent();
            logger.info("Searching book on site = " + link);
            Document document = userAgent.visit(link);
            return searchBooks(document);
        } catch (ResponseException e) {
            logger.error(e.getStackTrace().toString());
        }
        return Collections.emptyList();
    }

    /**
     * @param document where books are search
     * @return list of books found in document
     */

    public List<Book> searchBooks(Document document) {
        ArrayList<Book> books=new ArrayList<>();

        List<String> pathToTitleElement = BookSearchUtils.getPathToElement(bookStore.getSearchForTitle());
        List<String> pathToCategoryElement = BookSearchUtils.getPathToElement(bookStore.getSearchForCategory());

        Elements titleElements = document.findEvery(pathToTitleElement.get(1));
        Elements categoryElements = document.findEvery(pathToTitleElement.get(1));

        for (Element titleElement : titleElements) {
            String title = searchElement(titleElements, pathToTitleElement);
            String category = searchElement(categoryElements, pathToCategoryElement);
            Book book=new Book(title, category);
            books.add(book);
        }
        return books;
    }



    String searchElement(Element element, List<String> pathToTitleElement) {
        Element searchElement=null;

        try {
            for (int i = 2; i < pathToTitleElement.size(); i=i+2) {
                Elements pathToElement = element.findEvery(pathToTitleElement.get(i + 1));
                searchElement= pathToElement.getElement(Integer.valueOf(pathToTitleElement.get(i)));
                element=searchElement;
            }
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

        return searchElement.getText();

    }


    @Override
    public String toString() {
        return "BookSearch [" + bookStore.toString() + "]";
    }
}
