package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
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
        Iterator<String> linksIterator = hyperLinks.iterator();

        while (linksIterator.hasNext()) {
            this.booksList.addAll(searchBooks(linksIterator.next()));
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

    public List<Book> searchBooks(Document document) {
        ArrayList<Book> books=new ArrayList<>();

        List<String> pathToTitleElement = BookSearcherUtils.getPathToElement(bookStore.getSearchForTitle());
        List<String> pathToCategoryElement = BookSearcherUtils.getPathToElement(bookStore.getSearchForCategory());

        Elements titleElements = document.findEvery(pathToTitleElement.get(1));
        Elements categoryElements = document.findEvery(pathToCategoryElement.get(1));

        String title=null;
        String previousTitle="";
        for (int i = 0; i < titleElements.size(); i++) {
            Book book=null;
            String category="brak";
            try {
                title = searchElement(titleElements.getElement(i), pathToTitleElement);
                category = searchElement(categoryElements.getElement(i), pathToCategoryElement);
            } catch (NotFound e) {
                logger.info("Did not find any Books on site "+ document.getUrl());
            } finally {
                if (!previousTitle.equals(title)) {
                    book = new Book(title, category, bookStore);
                    books.add(book);
                    logger.info("Finded book "+book);
                }
                previousTitle=title;
            }

        }
        return books;
    }

    private String searchElement(Element element, List<String> pathToElement) {
        try {
            for (int i = 2; i < pathToElement.size(); i=i+2) {
                Elements pathToElement1 = element.findEach(pathToElement.get(i+1));
                element= pathToElement1.getElement(Integer.parseInt(pathToElement.get(i))-1);
            }
        } finally {
            return element.getText().trim();
        }
    }


    @Override
    public String toString() {
        return "BookSearcher [" + bookStore.toString() + "]";
    }
}
