package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BookSearch {
    private Logger logger = org.apache.log4j.Logger.getLogger(BookSearch.class);

    private BookStore bookStore;
    private List<Book> booksList;

    public BookSearch(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    public List<Book> searchBooks() throws ResponseException, NotFound {
        LinkSearch linkSearch = new LinkSearch(bookStore);
        linkSearch.searchHyperlinksOnSiteAndSubsites();
        HashSet<String> hyperLinks = linkSearch.getHyperLinks();

        Iterator<String> linksIterator = hyperLinks.iterator();

        logger.info("Started Searching books");

        while (linksIterator.hasNext()) {
            searchBooksOnSite(linksIterator.next());
        }
        return booksList;
    }

    public void searchBooksOnSite(String link) throws ResponseException, NotFound {

        UserAgent userAgent = new UserAgent();
        Document visit = userAgent.visit(link);

        Elements bookElements = visit.findEvery(bookStore.getSearchForElement());

        Iterator<Element> bookElementsIterator = bookElements.iterator();
        while (bookElementsIterator.hasNext()) {

            try {
                Element bookElement = bookElementsIterator.next();
                Element elementTitle = bookElement.findFirst("<a class=\"word-break\">");

                String searchForCategory = bookStore.getSearchForCategory();

                Book book;
                if (searchForCategory != null) {
                    Element categoryElements = bookElement.findFirst("<ul class=\"item-details\">").findFirst("li");
                    book = new Book(elementTitle.getText(), categoryElements.getText(), bookStore);
                } else {
                    book = new Book(elementTitle.getText(), "brak", bookStore);
                }

                booksList.add(book);
                logger.info("Book added to database " + book.toString());
            } catch (NotFound notFound) {
                logger.error("NotFound exception");
                logger.error(notFound.getMessage());

            }

        }
    }


    @Override
    public String toString() {
        return "BookSearch [" + bookStore.toString() + "]";
    }


}
