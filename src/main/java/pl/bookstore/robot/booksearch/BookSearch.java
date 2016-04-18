package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.DAO.BookDAO;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.*;

public class BookSearch {
    private static Logger logger = Logger.getLogger(BookSearch.class);

    private BookStore bookStore;
    private List<Book> booksList;
    private BookDAO bookDAO;

    public BookSearch(BookStore bookStore) {
        this.bookStore = bookStore;
        booksList=new ArrayList<Book>();
        bookDAO=new BookDAO();
    }

    public List<Book> searchBooks() throws ResponseException, NotFound {
        HashSet<String> hyperLinks = searchLinksOnSite();
        Iterator<String> linksIterator = hyperLinks.iterator();

        while (linksIterator.hasNext()) {
            searchBooksOnSite(linksIterator.next());
        }

        return this.booksList;
    }

    HashSet<String> searchLinksOnSite() {
        LinkSearch linkSearch = new LinkSearch(bookStore);
        return linkSearch.searchHyperlinksOnSiteAndSubsites();
    }

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

        public List<Book> searchBooks (Document document){
            Elements bookElements = document.findEvery(this.bookStore.getSearchForBook());

            Iterator<Element> bookElementsIterator = bookElements.iterator();
            while (bookElementsIterator.hasNext()) {

                try {
                    Element bookElement = bookElementsIterator.next();
                    Element elementTitle = bookElement.findFirst(this.bookStore.getSearchForTitle());

                    String searchForCategory = this.bookStore.getSearchForCategory();

                    Book book;
                    if (searchForCategory != null) {
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
