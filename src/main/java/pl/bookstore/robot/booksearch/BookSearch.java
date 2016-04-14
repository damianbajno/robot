package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BookSearch {
    private static Logger logger = Logger.getLogger(BookSearch.class);

    private BookStore bookStore;
    private List<Book> booksList;

    public BookSearch(BookStore bookStore) {
        this.bookStore = bookStore;
        booksList=new ArrayList<Book>();
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
        linkSearch.searchHyperlinksOnSiteAndSubsites();
        return linkSearch.getHyperLinks();
    }

    public List<Book> searchBooksOnSite(String link) throws ResponseException, NotFound {
        UserAgent userAgent = new UserAgent();
        Document document = userAgent.visit(link);
        return searchBooks(document);
    }

    public List<Book> searchBooks(Document document) {
        Elements bookElements = document.findEvery(this.bookStore.getSearchForElement());

        Iterator<Element> bookElementsIterator = bookElements.iterator();
        while (bookElementsIterator.hasNext()) {

            try {
                Element bookElement = bookElementsIterator.next();
                Element elementTitle = bookElement.findFirst(this.bookStore.getSearchForTitle());

                String searchForCategory = this.bookStore.getSearchForCategory();

                Book book;
                if (searchForCategory != null) {
                    Element categoryElements = bookElement.findFirst(this.bookStore.getSearchForCategory()).findFirst("li");
                    book = new Book(elementTitle.getText().trim(), categoryElements.getText().trim(), this.bookStore);
                } else {
                    book = new Book(elementTitle.getText(), "brak", this.bookStore);
                }

                this.booksList.add(book);
                logger.info("Book added to database " + book.toString());
            } catch (NotFound notFound) {
                logger.error(notFound.getMessage());

            }

        }
        return this.booksList;
    }


    @Override
    public String toString() {
        return "BookSearch [" + bookStore.toString() + "]";
    }


}
